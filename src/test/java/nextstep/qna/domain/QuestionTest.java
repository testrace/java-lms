package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {

    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @DisplayName("자신이 작성한 질문만 삭제할 수 있다")
    @Test
    void delete() {
        final List<DeleteHistory> actual = Q1.delete(NsUserTest.JAVAJIGI);

        final List<DeleteHistory> expected = List.of(new DeleteHistory(ContentType.QUESTION, Q1.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now()));

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("자신의 작성한 질문이 아니면 삭제할 수 없다")
    @Test
    void delete_exception() {
        assertThatThrownBy(() -> Q2.delete(NsUserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessage("질문을 삭제할 권한이 없습니다.");
    }
}
