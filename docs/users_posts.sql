create table users_posts(
	id SERIAL PRIMARY KEY,
    user_id bigint,
    post_id bigint
);