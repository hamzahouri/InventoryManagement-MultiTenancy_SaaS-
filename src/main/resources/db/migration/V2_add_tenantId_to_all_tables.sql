
alter table public.categories
    add tenant_id varchar(255) not null;

alter table produit
    add tenant_id varchar(255) not null;

alter table stock_mvts
    add tenant_id varchar(255) not null;

