CREATE TABLE IF NOT EXISTS first_benefits
(
    id                  VARCHAR(36)  	PRIMARY KEY,
    order_id         	VARCHAR(36) 	NOT NULL,
    user_id             VARCHAR(36) 	NOT NULL,
    status              VARCHAR(20)     NOT NULL,
    expired_at          TIMESTAMP WITH TIME ZONE NOT NULL,
    created_at          TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    modified_at         TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    deleted_at          TIMESTAMP WITH TIME ZONE,
    version             BIGINT          NOT NULL
);

CREATE INDEX IF NOT EXISTS first_benefits_idx_1 ON first_benefits(user_id, order_id);
