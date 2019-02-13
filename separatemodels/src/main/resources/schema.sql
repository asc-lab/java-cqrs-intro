drop table IF EXISTS policy_info_dto;
drop table IF EXISTS policy_version_cover_dto;
drop table IF EXISTS policy_version_dto;

create table policy_info_dto
(
  id                   bigserial primary key,
  policy_id            uuid                   not null,
  policy_number        character varying(50)  not null,
  cover_from           date                   not null,
  cover_to             date                   not null,
  vehicle              character varying(150) not null,
  policy_holder        character varying(50)  not null,
  total_premium_amount numeric(19, 2)         not null
);

create table policy_version_dto
(
  id                   bigserial primary key,
  policy_version_id    uuid                   not null,
  policy_id            uuid                   not null,
  policy_number        character varying(50)  not null,
  product_code         character varying(50)  not null,
  version_number       int                    not null,
  version_status       character varying(50)  not null,
  policy_status        character varying(50)  not null,
  policy_holder        character varying(250) not null,
  insured              character varying(250) not null,
  car                  character varying(250) not null,
  cover_from           date                   not null,
  cover_to             date                   not null,
  version_from         date                   not null,
  version_to           date                   not null,
  total_premium_amount numeric(19, 2)         not null
);

create table policy_version_cover_dto
(
  id                     bigserial primary key,
  policy_version_dto     bigint                not null references policy_version_dto (id),
  -- This column is required if we want to use List<> instead Set<> in PolicyVersionDto. Check this: https://www.youtube.com/watch?v=ccxBXDAPdmo
  policy_version_dto_key integer               not null,
  code                   character varying(50) not null,
  cover_from             date                  not null,
  cover_to               date                  not null,
  premium_amount         numeric(19, 2)        not null
);