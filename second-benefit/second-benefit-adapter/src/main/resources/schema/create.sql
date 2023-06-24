CREATE TABLE IF NOT EXISTS second_benefits
(
    id                  VARCHAR(36)  	PRIMARY KEY,
    user_id             VARCHAR(36) 	NOT NULL,
    type                VARCHAR(20)     NOT NULL,
    status              VARCHAR(20)     NOT NULL,
    expired_at          TIMESTAMP WITH TIME ZONE NOT NULL,
    created_at          TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    modified_at         TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    version             BIGINT          NOT NULL
);

CREATE INDEX IF NOT EXISTS second_benefits_idx_1 ON second_benefits(user_id);

CREATE TABLE IF NOT EXISTS second_benefit_target_mappings
(
    second_benefit_id    VARCHAR(36)  	PRIMARY KEY,
    target_id         	VARCHAR(36) 	NOT NULL,
    version             BIGINT          NOT NULL
);
