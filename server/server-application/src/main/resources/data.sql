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
INSERT INTO member_stub (member_name) VALUES
                                          ('John Doe'),
                                          ('Jane Smith'),
                                          ('Michael Johnson');


INSERT INTO question (title, detail, solution_status, answer_count, votes_count, view_count, member_id) VALUES
                                                                                                            ('Question 1', 'Question 1 detail', true, 3, 5, 10, (SELECT member_id FROM member_stub WHERE member_id = 1)),
                                                                                                            ('Question 2', 'Question 2 detail', false, 2, 8, 15, (SELECT member_id FROM member_stub WHERE member_id = 2)),
                                                                                                            ('Question 3', 'Question 3 detail', true, 1, 2, 7, (SELECT member_id FROM member_stub WHERE member_id = 3));


INSERT INTO question_tag (question_id, tag_id) VALUES
                                                   ((SELECT question_id FROM question WHERE question_id = 1), 1),
                                                   ((SELECT question_id FROM question WHERE question_id = 1), 2),
                                                   ((SELECT question_id FROM question WHERE question_id = 2), 2),
                                                   ((SELECT question_id FROM question WHERE question_id = 2), 3),
                                                   ((SELECT question_id FROM question WHERE question_id = 3), 1),
                                                   ((SELECT question_id FROM question WHERE question_id = 3), 3);


INSERT INTO question_vote (question_id, member_id, is_question_voted) VALUES
                                                                          ((SELECT question_id FROM question WHERE question_id = 1), (SELECT member_id FROM member_stub WHERE member_id = 1), true),
                                                                          ((SELECT question_id FROM question WHERE question_id = 1), (SELECT member_id FROM member_stub WHERE member_id = 2), true),
                                                                          ((SELECT question_id FROM question WHERE question_id = 2), (SELECT member_id FROM member_stub WHERE member_id = 2), true),
                                                                          ((SELECT question_id FROM question WHERE question_id = 3), (SELECT member_id FROM member_stub WHERE member_id = 3), true);

