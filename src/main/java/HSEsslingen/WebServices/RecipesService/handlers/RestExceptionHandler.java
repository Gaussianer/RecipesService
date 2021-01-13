package HSEsslingen.WebServices.RecipesService.handlers;


import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import HSEsslingen.WebServices.RecipesService.errors.ApiError;
import HSEsslingen.WebServices.RecipesService.exceptions.FieldAttributeNotFoundException;
import HSEsslingen.WebServices.RecipesService.exceptions.MissingAttributeWhileCreatingRecipeException;
import HSEsslingen.WebServices.RecipesService.exceptions.RecipeNotFoundException;
import HSEsslingen.WebServices.RecipesService.exceptions.RecipeNotFoundWithFilterAttributs;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.Arrays;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle MissingServletRequestParameterException. Triggered when a 'required' request parameter is missing.
     *
     * @param ex      MissingServletRequestParameterException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing";
        return buildResponseEntity(new ApiError(400, error, ex), BAD_REQUEST);
    }

    /**
     * Handle HttpMediaTypeNotSupportedException. This one triggers when JSON is invalid as well.
     *
     * @param ex      HttpMediaTypeNotSupportedException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
        ApiError apiError = new ApiError(415);
        apiError.setDetails(builder.substring(0, builder.length() - 2));
        apiError.setSummary(ex.getLocalizedMessage());
        apiError.setType("https://httpstatuses.com/415");
        apiError.setErrorCode("R-1001");
        apiError.setInformation("http://recipe-service/errors/R-0100");
        return buildResponseEntity(apiError, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * Handle MethodArgumentNotValidException. Triggered when an object fails @Valid validation.
     *
     * @param ex      the MethodArgumentNotValidException that is thrown when @Valid validation fails
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ApiError apiError = new ApiError(400);
        apiError.setSummary("Validation error");
        apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
        apiError.addValidationError(ex.getBindingResult().getGlobalErrors());
        return buildResponseEntity(apiError, BAD_REQUEST);
    }

    /**
     * Handles javax.validation.ConstraintViolationException. Thrown when @Validated fails.
     *
     * @param ex the ConstraintViolationException
     * @return the ApiError object
     */
    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(
            javax.validation.ConstraintViolationException ex) {
        ApiError apiError = new ApiError(400);
        apiError.setSummary("Validation error");
        apiError.addValidationErrors(ex.getConstraintViolations());
        return buildResponseEntity(apiError, BAD_REQUEST);
    }

    /**
     * Handles RecipeNotFoundException. Created to encapsulate errors with more detail than javax.persistence.RecipeNotFoundException.
     *
     * @param ex the RecipeNotFoundException
     * @return the ApiError object
     */
    @ExceptionHandler(RecipeNotFoundException.class)
    protected ResponseEntity<Object> handleRecipeNotFound(RecipeNotFoundException ex) {
        ApiError apiError = new ApiError(404);
        apiError.setType("https://httpstatuses.com/404");
        apiError.setSummary("Recipe could not be found");
        apiError.setDetails(ex.getMessage() + " Probably the UUID is incorrect. Check whether the UUID really exists.");
        apiError.setErrorCode("R-0100");
        apiError.setInformation("http://application-name/errors/R-0100");
        apiError.setUuid(ex.getID());
        return buildResponseEntity(apiError, NOT_FOUND);
    }

    /**
     * Handles EntityNotFoundException. Created to encapsulate errors with more detail than javax.persistence.EntityNotFoundException.
     *
     * @param ex the EntityNotFoundException
     * @return the ApiError object
     */
    @ExceptionHandler(RecipeNotFoundWithFilterAttributs.class)
    protected ResponseEntity<Object> handleEntityNotFound(
        RecipeNotFoundWithFilterAttributs ex) {
            ApiError apiError = new ApiError(404);
            apiError.setType("https://httpstatuses.com/404");
            apiError.setSummary("No recipe with the specified filter attributes could be found.");
            apiError.setDetails("There is no recipe that fulfils the conditions of the filter attributes: " + ex.getSpecificationKeyValuePairs().toString() + " You may want to check the values of the filter attributes. Alternatively, you can change the condition.");
            apiError.setErrorCode("R-0104");
            apiError.setInformation("http://application-name/errors/R-0104");
            return buildResponseEntity(apiError, NOT_FOUND);
    }

        /**
     * Handles EntityNotFoundException. Created to encapsulate errors with more detail than javax.persistence.EntityNotFoundException.
     *
     * @param ex the EntityNotFoundException
     * @return the ApiError object
     */
    @ExceptionHandler(MissingAttributeWhileCreatingRecipeException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
        MissingAttributeWhileCreatingRecipeException ex) {
            List<String> missingAttributesList = Arrays.asList(ex.getMissingAttributes());
            ApiError apiError = new ApiError(422);
            apiError.setType("https://httpstatuses.com/422");
            apiError.setSummary("The recipe could not be created due to missing attributes");
            String details = "No recipe could be created due to missing attributes. The missing attributes are: " + missingAttributesList + " Make sure that the attributes are present. They must not be NULL.";
            apiError.setDetails(details);
            apiError.setErrorCode("R-0105");
            apiError.setInformation("http://application-name/errors/R-0105");
            return buildResponseEntity(apiError, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    
    /**
     * Handles RecipeNotFoundException. Created to encapsulate errors with more detail than javax.persistence.RecipeNotFoundException.
     *
     * @param ex the RecipeNotFoundException
     * @return the ApiError object
     */
    @ExceptionHandler(FieldAttributeNotFoundException.class)
    protected ResponseEntity<Object> handleFieldAttributeNotFound(FieldAttributeNotFoundException ex) {
        ApiError apiError = new ApiError(422);
        apiError.setType("https://httpstatuses.com/422");
        apiError.setSummary("One of the specified field attributes could not be found during filtering");
        String details = "The following attributes could not be found when filtering using the field attributes: { ";
        String[] params = ex.getParams();

        if (params.length == 1){
            details = details + params[0];
        } else {
            for(int i = 0; i < params.length; i++) {
                if (i == 0) {
                    details = details + params[i] + ",";
                } else if (i == params.length -1) {
                    details = details + params[i];
                } else {
                    details = "," + details + params[i];
                }
            }
        }
        details = details + " }";
        apiError.setDetails(details);
        apiError.setErrorCode("R-0101");
        apiError.setInformation("http://recipe-service/errors/R-0101");
        apiError.setUuid(ex.getID());
        return buildResponseEntity(apiError, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Handle HttpMessageNotReadableException. Happens when request JSON is malformed.
     *
     * @param ex      HttpMessageNotReadableException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        log.info("{} to {}", servletWebRequest.getHttpMethod(), servletWebRequest.getRequest().getServletPath());
        String error = "Malformed JSON request";
        ApiError apiError = new ApiError(400, error, ex);
        apiError.setType("https://httpstatuses.com/400");
        apiError.setErrorCode("R-1002");
        apiError.setInformation("http://recipe-service/errors/R-1002");

        return buildResponseEntity(apiError, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle HttpMessageNotReadableException. Happens when request JSON is malformed.
     *
     * @param ex      HttpMessageNotReadableException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        log.info("{} to {}", servletWebRequest.getHttpMethod(), servletWebRequest.getRequest().getServletPath());
        String error = "Http request method is not supported";
        ApiError apiError = new ApiError(405, error, ex);
        apiError.setType("https://httpstatuses.com/405");
        apiError.setErrorCode("R-1003");
        apiError.setInformation("http://recipe-service/errors/R-1003");

        return buildResponseEntity(apiError, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * Handle HttpMessageNotWritableException.
     *
     * @param ex      HttpMessageNotWritableException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Error writing JSON output";
        return buildResponseEntity(new ApiError(500, error, ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle NoHandlerFoundException.
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = new ApiError(400);
        apiError.setSummary(String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL()));
        apiError.setDetails(ex.getMessage());
        return buildResponseEntity(apiError, BAD_REQUEST);
    }

    /**
     * Handle DataIntegrityViolationException, inspects the cause for different DB causes.
     *
     * @param ex the DataIntegrityViolationException
     * @return the ApiError object
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
                                                                  WebRequest request) {
        if (ex.getCause() instanceof ConstraintViolationException) {
            return buildResponseEntity(new ApiError(409, "Database error", ex.getCause()), HttpStatus.CONFLICT);
        }
        return buildResponseEntity(new ApiError(500, ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle Exception, handle generic Exception.class
     *
     * @param ex the Exception
     * @return the ApiError object
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        ApiError apiError = new ApiError(400);
        apiError.setType("https://httpstatuses.com/400");
        apiError.setErrorCode("R-1004");
        apiError.setSummary(String.format("The parameter '%s' of value '%s' could not be converted to type '%s'", ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName()));
        apiError.setDetails(ex.getMessage());
        apiError.setInformation("http://recipe-service/errors/R-1004");
        return buildResponseEntity(apiError, BAD_REQUEST);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError, HttpStatus httpStatus) {
        return new ResponseEntity<>(apiError, httpStatus);
    }

}
