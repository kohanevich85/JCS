DROP TABLE IF EXISTS ci_claim_build;
CREATE TABLE ci_claim_build (
	 id INTEGER AUTO_INCREMENT,
	 job_name VARCHAR_IGNORECASE(255),
	 claimed_by VARCHAR_IGNORECASE(100),
	 reason VARCHAR_IGNORECASE(1000) NOT NULL,
	 start_date_claim TIMESTAMP,
	 end_date_claim TIMESTAMP,
	 PRIMARY KEY (id),
	 UNIQUE (job_name,start_date_claim)
);

CREATE INDEX IF NOT EXISTS IDXCLAIM_BY ON CI_claim_build(claimed_by);
CREATE INDEX IF NOT EXISTS IDXJOB_NAME ON CI_claim_build(job_name);
CREATE INDEX IF NOT EXISTS IDXSTART ON CI_claim_build(start_date_claim);
CREATE INDEX IF NOT EXISTS IDXEND ON CI_claim_build(end_date_claim);
