package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {

    private final List<Answer> answers = new ArrayList<>();

    public Answers() {
    }

    public Answers(List<Answer> answers) {
        this.answers.addAll(answers);
    }

    public void add(final Answer answer) {
        answers.add(answer);
    }

    public List<DeleteHistory> delete(NsUser loginUser) {
        validateOwner(loginUser);

        final List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (final Answer answer : answers) {
            deleteHistories.add(answer.delete(loginUser));
        }

        return deleteHistories;
    }

    private void validateOwner(final NsUser loginUser) {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    private boolean isOwner(final NsUser loginUser) {
        return answers.stream()
            .allMatch(answer -> answer.isOwner(loginUser));
    }
}
