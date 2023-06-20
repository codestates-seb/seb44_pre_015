CREATE TABLE IF NOT EXISTS member_stub (
                                           member_id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                           member_name VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS question (
                                        question_id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                        title VARCHAR(255) NOT NULL,
    detail TEXT NOT NULL,
    solution_status BOOLEAN NOT NULL,
    answer_count INT NOT NULL,
    votes_count INT NOT NULL,
    view_count INT NOT NULL,
    member_id BIGINT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (member_id) REFERENCES member_stub (member_id)
    );
CREATE TABLE IF NOT EXISTS question_vote (
                                             question_vote_id SERIAL PRIMARY KEY,
                                             question_id BIGINT NOT NULL,
                                             member_id BIGINT NOT NULL,
                                             is_question_voted BOOLEAN NOT NULL,
                                             FOREIGN KEY (question_id) REFERENCES question(question_id),
    FOREIGN KEY (member_id) REFERENCES member_stub(member_id)
    );





CREATE TABLE IF NOT EXISTS tag (
                                   tag_id SERIAL PRIMARY KEY,
                                   tag_name VARCHAR(255) NOT NULL,
    tag_description VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS question_tag (
                                            question_tag_id SERIAL PRIMARY KEY,
                                            question_id BIGINT NOT NULL,
                                            tag_id BIGINT NOT NULL,
                                            FOREIGN KEY (question_id) REFERENCES question(question_id),
    FOREIGN KEY (tag_id) REFERENCES tag(tag_id)
    );

