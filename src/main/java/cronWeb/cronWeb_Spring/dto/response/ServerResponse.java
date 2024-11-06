package cronWeb.cronWeb_Spring.dto.response;

import lombok.Data;

import java.util.Map;

@Data
public class ServerResponse {
    private int status; // 상태 값
    private String message; // 필드 메시지
    private Map<String, String> errorData; // 에러 데이터

    public ServerResponse(int status, String message, Map<String, String> errorData) {
        this.status = status;
        this.message = message;
        this.errorData = errorData;
    }
}
