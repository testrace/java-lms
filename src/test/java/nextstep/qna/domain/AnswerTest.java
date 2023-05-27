package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AnswerTest {

    private Answer answer;

    @BeforeEach
    void setUp() {
        answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    }

    @DisplayName("자신이 작성한 답변만 삭제할 수 있다")
    @Test
    void delete() {
        answer.delete(NsUserTest.JAVAJIGI);
        final boolean actual = answer.isDeleted();

        assertThat(actual).isTrue();
    }

    @DisplayName("자신이 작성한 답변을 삭제하면 예외를 던진다")
    @Test
    void delete_exception() {
        assertThatThrownBy(() -> answer.delete(NsUserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessage("자신이 작성한 답변만 삭제 가능합니다");
    }
}
