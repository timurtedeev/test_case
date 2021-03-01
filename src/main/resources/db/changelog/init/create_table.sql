create table csv_import (
  id serial PRIMARY KEY,
  ssoid varchar(255),
  ts timestamp,
  grp varchar (255),
  type  varchar (255),
  subtype  varchar (255),
  url varchar (255),
  orgid varchar (255),
  formid varchar (255),
  code varchar (255),
  ltpa varchar (255),
  sudirresponse varchar (255),
  ymdh timestamp
);