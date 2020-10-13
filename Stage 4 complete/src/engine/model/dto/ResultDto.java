package engine.model.dto;

public class ResultDto {
    private boolean success;
    private String feedback;

    ResultDto(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public static ResultDto success() {
        return new ResultDto(true, "Congratulations, you're right!");
    }

    public static ResultDto wrong() {
        return new ResultDto(false, "Wrong answer! Please, try again.");
    }

}
