package id.dikisiswanto.jetpackacademy.data.source.remote;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static id.dikisiswanto.jetpackacademy.data.source.remote.StatusResponse.EMPTY;
import static id.dikisiswanto.jetpackacademy.data.source.remote.StatusResponse.ERROR;
import static id.dikisiswanto.jetpackacademy.data.source.remote.StatusResponse.SUCCESS;

public class ApiResponse<T> {
	@NonNull
	public final StatusResponse status;

	@Nullable
	public final String message;

	@Nullable
	public final T body;


	public ApiResponse(@NonNull StatusResponse status, @Nullable T body, @Nullable String message) {
		this.status = status;
		this.message = message;
		this.body = body;
	}

	public static <T> ApiResponse<T> success(@Nullable T body) {
		return new ApiResponse<>(SUCCESS, body, null);
	}

	public static <T> ApiResponse<T> empty(String message, @Nullable T body) {
		return new ApiResponse<>(EMPTY, body, message);
	}

	public static <T> ApiResponse<T> error(String message, @Nullable T body) {
		return new ApiResponse<>(ERROR, body, message);
	}

}
