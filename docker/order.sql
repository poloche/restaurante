-- public.bill definition

-- Drop table

-- DROP TABLE public.bill;

CREATE TABLE public.bill (
	id serial4 NOT NULL,
	credit_card_number varchar NULL,
	billing_address varchar NULL,
	payment_method varchar NULL,
	CONSTRAINT bill_pk PRIMARY KEY (id)
);


-- public.product definition

-- Drop table

-- DROP TABLE public.product;

CREATE TABLE public.product (
	id serial4 NOT NULL,
	nombre varchar NOT NULL,
	description varchar NOT NULL,
	image varchar NULL,
	"cost" float8 DEFAULT 0 NOT NULL,
	CONSTRAINT product_pk PRIMARY KEY (id)
);


-- public.vehicle definition

-- Drop table

-- DROP TABLE public.vehicle;

CREATE TABLE public.vehicle (
	vehicle_id serial4 NOT NULL,
	plate varchar NOT NULL,
	CONSTRAINT vehicle_pk PRIMARY KEY (vehicle_id),
	CONSTRAINT vehicle_unique UNIQUE (plate)
);


-- public.restaurant definition

-- Drop table

-- DROP TABLE public.restaurant;

CREATE TABLE public.restaurant (
	id serial4 NOT NULL,
	"owner" varchar NOT NULL,
	cellphone varchar NOT NULL,
	category varchar NOT NULL,
	"location" varchar NOT NULL,
	billing int4 NULL,
	CONSTRAINT restaurant_pk PRIMARY KEY (id),
	CONSTRAINT restaurant_bill_fk FOREIGN KEY (billing) REFERENCES public.bill(id)
);


-- public."user" definition

-- Drop table

-- DROP TABLE public."user";

CREATE TABLE public."user" (
	id serial4 NOT NULL,
	"name" varchar NOT NULL,
	address varchar NULL,
	celphone varchar NULL,
	facebook_id varchar NULL,
	"location" varchar NULL,
	billing int8 NULL,
	user_type varchar DEFAULT 'client'::character varying NOT NULL,
	vehicle int4 NULL,
	CONSTRAINT user_pk PRIMARY KEY (id),
	CONSTRAINT user_bill_fk FOREIGN KEY (billing) REFERENCES public.bill(id),
	CONSTRAINT user_vehicle_fk FOREIGN KEY (vehicle) REFERENCES public.vehicle(vehicle_id)
);


-- public.offer definition

-- Drop table

-- DROP TABLE public.offer;

CREATE TABLE public.offer (
	offer_id int4 DEFAULT nextval('offering_offering_id_seq'::regclass) NOT NULL,
	"date" date NOT NULL,
	restaurant_id int4 NULL,
	CONSTRAINT menu_pk PRIMARY KEY (offer_id),
	CONSTRAINT offering_restaurant_fk FOREIGN KEY (restaurant_id) REFERENCES public.restaurant(id)
);


-- public."order" definition

-- Drop table

-- DROP TABLE public."order";

CREATE TABLE public."order" (
	order_id serial4 NOT NULL,
	"date" date NOT NULL,
	client int4 NOT NULL,
	restaurant int4 NOT NULL,
	status varchar DEFAULT 'Ordered'::character varying NOT NULL,
	total float8 NULL,
	CONSTRAINT order_pk PRIMARY KEY (order_id),
	CONSTRAINT order_restaurant_fk FOREIGN KEY (restaurant) REFERENCES public.restaurant(id),
	CONSTRAINT order_user_fk FOREIGN KEY (client) REFERENCES public."user"(id)
);


-- public.order_product definition

-- Drop table

-- DROP TABLE public.order_product;

CREATE TABLE public.order_product (
	order_id int4 NOT NULL,
	product int4 NOT NULL,
	quantity int4 NOT NULL,
	CONSTRAINT order_product_pk PRIMARY KEY (order_id, product),
	CONSTRAINT order_product_order_fk FOREIGN KEY (order_id) REFERENCES public."order"(order_id),
	CONSTRAINT order_product_product_fk FOREIGN KEY (product) REFERENCES public.product(id)
);


-- public.product_offer definition

-- Drop table

-- DROP TABLE public.product_offer;

CREATE TABLE public.product_offer (
	product_id int4 NOT NULL,
	offer_id int4 NOT NULL,
	quantity int4 NOT NULL,
	CONSTRAINT product_offer_pk PRIMARY KEY (product_id, offer_id),
	CONSTRAINT product_offer_offer_fk FOREIGN KEY (offer_id) REFERENCES public.offer(offer_id),
	CONSTRAINT product_offer_product_fk FOREIGN KEY (product_id) REFERENCES public.product(id)
);