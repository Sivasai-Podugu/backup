INSERT INTO user (name, email, password) VALUES
                                                  ('Siva', 'siva@example.com', 'password_1'),
                                                  ('Jaan', 'jaan@example.com', 'password_2'),
                                                  ('Sai', 'sai@example.com', 'password_3'),
                                                  ('Arjun', 'arjun@example.com', 'password_4');

INSERT INTO note (user_id, title, content) VALUES
                                                (1, 'Meeting Notes', 'Discussed project timeline and deliverables.'),
                                                (2, 'Shopping List', 'Milk, eggs, bread, and vegetables.'),
                                                (3, 'Ideas for Blog Post', 'Topics: Machine Learning, Data Science.'),
                                                (4, 'Travel Itinerary', 'Flight details: Departure at 8 AM, Arrival at 3 PM.');

INSERT INTO tag (name) VALUES
                                ('Important'),
                                ('Personal'),
                                ('Work'),
                                ('Study'),
                                ('Shopping');


INSERT INTO note_tags (note_id, tag_id) VALUES
                                            (1, 1),
                                            (1, 3),
                                            (2, 5),
                                            (3, 4),
                                            (4, 2);



