--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3
-- Dumped by pg_dump version 15.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: debt; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.debt (
    name character varying(200),
    price double precision,
    return double precision,
    risk double precision
);


ALTER TABLE public.debt OWNER TO postgres;

--
-- Name: equity; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.equity (
    name character varying(200),
    price double precision,
    return double precision,
    risk double precision
);


ALTER TABLE public.equity OWNER TO postgres;

--
-- Name: real_estate; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.real_estate (
    name character varying(200),
    price double precision,
    return double precision,
    risk double precision
);


ALTER TABLE public.real_estate OWNER TO postgres;

--
-- Data for Name: debt; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.debt (name, price, return, risk) FROM stdin;
ICICI	1000	7.46	4
Nippon	2000	8.2	3
HDFC	3000	7.1	4.3
Small Finance Bank	5000	9.1	7.3
\.


--
-- Data for Name: equity; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.equity (name, price, return, risk) FROM stdin;
Nifty	1000	13.6	13.3
Tata	2000	15	12.3
Bajaj Auto	3000	20	14.53
Adani	4000	30	15.24
Sun Pharma	3000	20	17.23
\.


--
-- Data for Name: real_estate; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.real_estate (name, price, return, risk) FROM stdin;
Delhi	2000	20	25.31
Mumbai	3000	25	22.95
Hyderabad	1700	27	29.3
Chennai	3000	26	24.32
\.


--
-- PostgreSQL database dump complete
--

