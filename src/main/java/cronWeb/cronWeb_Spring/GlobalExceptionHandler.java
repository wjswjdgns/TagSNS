package cronWeb.cronWeb_Spring.exception;

import cronWeb.cronWeb_Spring.UserException.DuplicateName;
import cronWeb.cronWeb_Spring.UserException.DuplicateUniqueId;
import cronWeb.cronWeb_Spring.UserException.NotMatchPassword;
import cronWeb.cronWeb_Spring.dto.response.ServerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // DTO 검사 실패시
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ServerResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errorData = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errorData.put(error.getField(), error.getDefaultMessage());
        }

        ServerResponse response = new ServerResponse(HttpStatus.BAD_REQUEST.value(), "회원가입 실패", errorData);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // 아이디 중복 검사
    @ExceptionHandler(DuplicateName.class)
    public ResponseEntity<ServerResponse> handleDuplicateName(DuplicateName e) {
        Map<String, String> errorData = new HashMap<>();
        errorData.put("name", e.getMessage()); // DuplicateName 예외의 메시지를 errorData에 추가
        ServerResponse response = new ServerResponse(HttpStatus.BAD_REQUEST.value(), "회원가입 실패", errorData);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // 고유 ID 중복 검사
    @ExceptionHandler(DuplicateUniqueId.class)
    public ResponseEntity<ServerResponse> handleDuplicateUniqueId(DuplicateUniqueId e) {
        Map<String, String> errorData = new HashMap<>();
        errorData.put("uniqueId", e.getMessage()); // DuplicateUniqueId 예외의 메시지를 errorData에 추가
        ServerResponse response = new ServerResponse(HttpStatus.BAD_REQUEST.value(), "회원가입 실패", errorData);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // 비밀번호 형식 검사
    @ExceptionHandler(NotMatchPassword.class)
    public ResponseEntity<ServerResponse> handleNotMatchPassword(NotMatchPassword e) {
        Map<String, String> errorData = new HashMap<>();
        errorData.put("password", e.getMessage()); // NotMatchPassword 예외의 메시지를 errorData에 추가
        ServerResponse response = new ServerResponse(HttpStatus.BAD_REQUEST.value(), "회원가입 실패", errorData);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    // 런타임 에러
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ServerResponse> handleServer(RuntimeException e) {
        ServerResponse response = new ServerResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
