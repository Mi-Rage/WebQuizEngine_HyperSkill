package engine.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class Quiz {

    private int id;

    @NotEmpty(message = "Quiz title must be not Empty")
    @NotNull(message = "Quiz title must be not Null")
    private String title;

    @NotEmpty(message = "Quiz text must be not Empty")
    @NotNull(message = "Quiz title must be not Null")
    private String text;

    @Size(min = 2, message = "Quiz options must be min 2 elem")
    private String[] options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Integer> answer = new HashSet<>();

    public Quiz() {
    }

    public Quiz(int id, String title, String text, String[] options, Set<Integer> answer) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public Set<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(Set<Integer> answer) {
        this.answer = answer;
    }
}
