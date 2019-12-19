package pe.com.bcp.api.webdto.response;

import pe.com.bcp.api.exception.ErrorCode;

public class BaseWebResponse<T> {
    private ErrorCode errorCode;
    private T data;

    public static BaseWebResponse successNoData() {
        
    	BaseWebResponse objeto = new BaseWebResponse();
    	
    	return objeto;
    }

    public static <T> BaseWebResponse<T> successWithData(T data) {
    	
    	BaseWebResponse<T> objeto = new BaseWebResponse();
    	objeto.setData(data);
    	return objeto;
    	
    }

    public static BaseWebResponse error(ErrorCode errorCode) {
    	BaseWebResponse objeto = new BaseWebResponse();
    	objeto.setErrorCode(errorCode);;
    	return objeto;
    }

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
