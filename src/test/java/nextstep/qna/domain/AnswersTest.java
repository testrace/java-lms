package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AnswersTest {

    private Answer myFirstAnswer;
    private Answer mySecondAnswer;
    private Answer someoneAnswer;

    @BeforeEach
    void setUp() {
        myFirstAnswer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        mySecondAnswer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2");
        someoneAnswer = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents3");
    }

    @DisplayName("자신의 답변만 있는 경우 삭제할 수 있다")
    @Test
    void delete() {
        //given
        final Answers answers = new Answers(
            List.of(myFirstAnswer, mySecondAnswer)
        );

        //when
        final List<DeleteHistory> actual = answers.delete(NsUserTest.JAVAJIGI);

        //then
        final List<DeleteHistory> expected = List.of(
            new DeleteHistory(ContentType.ANSWER, myFirstAnswer.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now()),
            new DeleteHistory(ContentType.ANSWER, mySecondAnswer.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now())
        );

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("다른 사람의 답변이 포함된 경우 삭제할 수 없다")
    @Test
    void delete_exception() {
        final Answers answers = new Answers(
            List.of(myFirstAnswer, someoneAnswer)
        );
        assertThatThrownBy(() -> answers.delete(NsUserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

}
