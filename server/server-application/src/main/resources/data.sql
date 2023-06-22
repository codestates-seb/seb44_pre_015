INSERT INTO tag (tag_name, tag_description) VALUES
    ('java', 'Java programming language'),
    ('spring-boot', 'Spring Boot framework'),
    ('python', 'Python programming language'),
    ('javascript', 'JavaScript programming language'),
    ('html', 'HTML markup language'),
    ('css', 'CSS styling language'),
    ('database', 'Database management systems'),
    ('git', 'Version control system'),
    ('android', 'Android operating system'),
    ('ios', 'iOS operating system');

INSERT INTO member (name, email, picture, answer_vote_count) VALUES
    ('John Doe', 'johndoe@example.com', 'profile1.jpg', 0),
    ('Jane Smith', 'janesmith@example.com', 'profile2.jpg', 0),
    ('Michael Johnson', 'michaeljohnson@example.com', 'profile3.jpg', 0),
    ('Emily Davis', 'emilydavis@example.com', 'profile4.jpg', 0),
    ('Daniel Wilson', 'danielwilson@example.com', 'profile5.jpg', 0),
    ('Sophia Anderson', 'sophiaanderson@example.com', 'profile6.jpg', 0),
    ('Matthew Taylor', 'matthewtaylor@example.com', 'profile7.jpg', 0),
    ('Olivia Brown', 'oliviabrown@example.com', 'profile8.jpg', 0),
    ('David Martinez', 'davidmartinez@example.com', 'profile9.jpg', 0),
    ('Emma Thomas', 'emmathomas@example.com', 'profile10.jpg', 0);

INSERT INTO question (title, detail, solution_status, answer_count, votes_count, view_count, member_id) VALUES
    ('Question 1', 'Detail of question 1', false, 0, 0, 0, 1),
    ('Question 2', 'Detail of question 2', false, 0, 0, 0, 2),
    ('Question 3', 'Detail of question 3', false, 0, 0, 0, 3),
    ('Question 4', 'Detail of question 4', false, 0, 0, 0, 4),
    ('Question 5', 'Detail of question 5', false, 0, 0, 0, 5);

--INSERT INTO tag (tag_name, tag_description) VALUES
--                                                ('java', 'Java programming language'),
--                                                ('spring-boot', 'Spring Boot framework'),
--                                                ('python', 'Python programming language'),
--                                                ('javascript', 'JavaScript programming language'),
--                                                ('html', 'HTML markup language'),
--                                                ('css', 'CSS styling language'),
--                                                ('database', 'Database management systems'),
--                                                ('git', 'Version control system'),
--                                                ('android', 'Android operating system'),
--                                                ('ios', 'iOS operating system');
--
--INSERT INTO member (name, email, picture, answer_vote_count) VALUES
--                                                                 ('John Doe', 'johndoe@example.com', 'profile1.jpg', 0),
--                                                                 ('Jane Smith', 'janesmith@example.com', 'profile2.jpg', 0),
--                                                                 ('Michael Johnson', 'michaeljohnson@example.com', 'profile3.jpg', 0),
--                                                                 ('Emily Davis', 'emilydavis@example.com', 'profile4.jpg', 0),
--                                                                 ('Daniel Wilson', 'danielwilson@example.com', 'profile5.jpg', 0),
--                                                                 ('Sophia Anderson', 'sophiaanderson@example.com', 'profile6.jpg', 0),
--                                                                 ('Matthew Taylor', 'matthewtaylor@example.com', 'profile7.jpg', 0),
--                                                                 ('Olivia Brown', 'oliviabrown@example.com', 'profile8.jpg', 0),
--                                                                 ('David Martinez', 'davidmartinez@example.com', 'profile9.jpg', 0),
--                                                                 ('Emma Thomas', 'emmathomas@example.com', 'profile10.jpg', 0);
--
--INSERT INTO question (question_id, title, detail, solution_status, answer_count, votes_count, view_count, member_id) VALUES
--                                                                                                                         (1, 'Question 1', 'Detail of question 1', false, 0, 0, 0, 1),
--                                                                                                                         (2, 'Question 2', 'Detail of question 2', false, 0, 0, 0, 2),
--                                                                                                                         (3, 'Question 3', 'Detail of question 3', false, 0, 0, 0, 3),
--                                                                                                                         (4, 'Question 4', 'Detail of question 4', false, 0, 0, 0, 4),
--                                                                                                                         (5, 'Question 5', 'Detail of question 5', false, 0, 0, 0, 5);

-- INSERT INTO question (title, detail, solution_status, answer_count, votes_count, view_count, member_id) VALUES
--                                                                                                             ('Question 1', 'Question 1 detail', true, 3, 5, 10, (SELECT member_id FROM member WHERE member_id = 1)),
--                                                                                                             ('Question 2', 'Question 2 detail', false, 2, 8, 15, (SELECT member_id FROM member WHERE member_id = 2)),
--                                                                                                             ('Question 3', 'Question 3 detail', true, 1, 2, 7, (SELECT member_id FROM member WHERE member_id = 3));
--
--
-- INSERT INTO question_tag (question_id, tag_id) VALUES
--                                                    ((SELECT question_id FROM question WHERE question_id = 1), 1),
--                                                    ((SELECT question_id FROM question WHERE question_id = 1), 2),
--                                                    ((SELECT question_id FROM question WHERE question_id = 2), 2),
--                                                    ((SELECT question_id FROM question WHERE question_id = 2), 3),
--                                                    ((SELECT question_id FROM question WHERE question_id = 3), 1),
--                                                    ((SELECT question_id FROM question WHERE question_id = 3), 3);
--
--
-- INSERT INTO question_vote (question_id, member_id, is_question_voted) VALUES
--                                                                           ((SELECT question_id FROM question WHERE question_id = 1), (SELECT member_id FROM member WHERE member_id = 1), true),
--                                                                           ((SELECT question_id FROM question WHERE question_id = 1), (SELECT member_id FROM member WHERE member_id = 2), true),
--                                                                           ((SELECT question_id FROM question WHERE question_id = 2), (SELECT member_id FROM member WHERE member_id = 2), true),
--                                                                           ((SELECT question_id FROM question WHERE question_id = 3), (SELECT member_id FROM member WHERE member_id = 3), true);

