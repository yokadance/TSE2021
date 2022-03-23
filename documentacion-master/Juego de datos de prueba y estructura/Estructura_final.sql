--
-- PostgreSQL database dump
--

-- Dumped from database version 13.2
-- Dumped by pg_dump version 13.2

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

--
-- Name: public; Type: SCHEMA; Schema: -; Owner: webadmin
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO webadmin;

--
-- Name: truncate_tables(character varying); Type: FUNCTION; Schema: public; Owner: webadmin
--

CREATE FUNCTION public.truncate_tables(username character varying) RETURNS void
    LANGUAGE plpgsql
    AS $$
DECLARE
    statements CURSOR FOR
        SELECT tablename FROM pg_tables
        WHERE tableowner = username AND schemaname = 'public';
BEGIN
    FOR stmt IN statements LOOP
        EXECUTE 'TRUNCATE TABLE ' || quote_ident(stmt.tablename) || ' CASCADE;';
    END LOOP;
END;
$$;


ALTER FUNCTION public.truncate_tables(username character varying) OWNER TO webadmin;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: assignment; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.assignment (
    id bigint NOT NULL,
    createdate timestamp without time zone,
    deletedate timestamp without time zone,
    updatedate timestamp without time zone,
    userid character varying(255),
    enddate character varying(255),
    endtime character varying(255),
    id_schedule bigint,
    id_vaccinator bigint,
    id_vpost bigint,
    startdate character varying(255),
    starttime character varying(255)
);


ALTER TABLE public.assignment OWNER TO webadmin;

--
-- Name: assignment_id_seq; Type: SEQUENCE; Schema: public; Owner: webadmin
--

CREATE SEQUENCE public.assignment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.assignment_id_seq OWNER TO webadmin;

--
-- Name: assignment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: webadmin
--

ALTER SEQUENCE public.assignment_id_seq OWNED BY public.assignment.id;


--
-- Name: authority_schedule; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.authority_schedule (
    authority_id bigint,
    id bigint NOT NULL
);


ALTER TABLE public.authority_schedule OWNER TO webadmin;

--
-- Name: authority_vaccinationplan; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.authority_vaccinationplan (
    authority_id bigint,
    id bigint NOT NULL
);


ALTER TABLE public.authority_vaccinationplan OWNER TO webadmin;

--
-- Name: batch; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.batch (
    id bigint NOT NULL,
    createdate timestamp without time zone,
    deletedate timestamp without time zone,
    updatedate timestamp without time zone,
    userid character varying(255),
    available integer NOT NULL,
    creationdate timestamp without time zone,
    expirationdate integer NOT NULL,
    number bigint NOT NULL,
    quantity integer NOT NULL
);


ALTER TABLE public.batch OWNER TO webadmin;

--
-- Name: batch_id_seq; Type: SEQUENCE; Schema: public; Owner: webadmin
--

CREATE SEQUENCE public.batch_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.batch_id_seq OWNER TO webadmin;

--
-- Name: batch_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: webadmin
--

ALTER SEQUENCE public.batch_id_seq OWNED BY public.batch.id;


--
-- Name: batch_package; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.batch_package (
    batch_id bigint,
    id bigint NOT NULL
);


ALTER TABLE public.batch_package OWNER TO webadmin;

--
-- Name: citizen_reservation; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.citizen_reservation (
    citizen_id bigint,
    id bigint NOT NULL
);


ALTER TABLE public.citizen_reservation OWNER TO webadmin;

--
-- Name: citizen_vaccinationact; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.citizen_vaccinationact (
    citizen_id bigint,
    id bigint NOT NULL
);


ALTER TABLE public.citizen_vaccinationact OWNER TO webadmin;

--
-- Name: datasource; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.datasource (
    id bigint NOT NULL,
    createdate timestamp without time zone,
    deletedate timestamp without time zone,
    updatedate timestamp without time zone,
    userid character varying(255),
    name character varying(255),
    url character varying(255),
    restriction_id bigint
);


ALTER TABLE public.datasource OWNER TO webadmin;

--
-- Name: datasource_id_seq; Type: SEQUENCE; Schema: public; Owner: webadmin
--

CREATE SEQUENCE public.datasource_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.datasource_id_seq OWNER TO webadmin;

--
-- Name: datasource_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: webadmin
--

ALTER SEQUENCE public.datasource_id_seq OWNED BY public.datasource.id;


--
-- Name: disease; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.disease (
    id bigint NOT NULL,
    createdate timestamp without time zone,
    deletedate timestamp without time zone,
    updatedate timestamp without time zone,
    userid character varying(255),
    name character varying(255),
    symptons character varying(255)
);


ALTER TABLE public.disease OWNER TO webadmin;

--
-- Name: disease_id_seq; Type: SEQUENCE; Schema: public; Owner: webadmin
--

CREATE SEQUENCE public.disease_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.disease_id_seq OWNER TO webadmin;

--
-- Name: disease_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: webadmin
--

ALTER SEQUENCE public.disease_id_seq OWNED BY public.disease.id;


--
-- Name: iot; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.iot (
    id bigint NOT NULL,
    createdate timestamp without time zone,
    deletedate timestamp without time zone,
    updatedate timestamp without time zone,
    userid character varying(255),
    date date,
    message character varying(255),
    logisticpartner_id bigint
);


ALTER TABLE public.iot OWNER TO webadmin;

--
-- Name: iot_id_seq; Type: SEQUENCE; Schema: public; Owner: webadmin
--

CREATE SEQUENCE public.iot_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.iot_id_seq OWNER TO webadmin;

--
-- Name: iot_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: webadmin
--

ALTER SEQUENCE public.iot_id_seq OWNED BY public.iot.id;


--
-- Name: laboratory; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.laboratory (
    id bigint NOT NULL,
    createdate timestamp without time zone,
    deletedate timestamp without time zone,
    updatedate timestamp without time zone,
    userid character varying(255),
    email character varying(255),
    name character varying(255),
    origin character varying(255),
    phone character varying(255)
);


ALTER TABLE public.laboratory OWNER TO webadmin;

--
-- Name: laboratory_id_seq; Type: SEQUENCE; Schema: public; Owner: webadmin
--

CREATE SEQUENCE public.laboratory_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.laboratory_id_seq OWNER TO webadmin;

--
-- Name: laboratory_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: webadmin
--

ALTER SEQUENCE public.laboratory_id_seq OWNED BY public.laboratory.id;


--
-- Name: logistic; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.logistic (
    id bigint NOT NULL,
    createdate timestamp without time zone,
    deletedate timestamp without time zone,
    updatedate timestamp without time zone,
    userid character varying(255),
    date date,
    typeevent integer,
    logisticpartner_id bigint
);


ALTER TABLE public.logistic OWNER TO webadmin;

--
-- Name: logistic_id_seq; Type: SEQUENCE; Schema: public; Owner: webadmin
--

CREATE SEQUENCE public.logistic_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.logistic_id_seq OWNER TO webadmin;

--
-- Name: logistic_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: webadmin
--

ALTER SEQUENCE public.logistic_id_seq OWNED BY public.logistic.id;


--
-- Name: logisticpartner; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.logisticpartner (
    id bigint NOT NULL,
    createdate timestamp without time zone,
    deletedate timestamp without time zone,
    updatedate timestamp without time zone,
    userid character varying(255),
    businessname character varying(255),
    email character varying(255),
    name character varying(255),
    password character varying(255),
    phone character varying(255),
    reference character varying(255),
    rut character varying(255)
);


ALTER TABLE public.logisticpartner OWNER TO webadmin;

--
-- Name: logisticpartner_id_seq; Type: SEQUENCE; Schema: public; Owner: webadmin
--

CREATE SEQUENCE public.logisticpartner_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.logisticpartner_id_seq OWNER TO webadmin;

--
-- Name: logisticpartner_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: webadmin
--

ALTER SEQUENCE public.logisticpartner_id_seq OWNED BY public.logisticpartner.id;


--
-- Name: logisticpartner_iot; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.logisticpartner_iot (
    logisticpartner_id bigint NOT NULL,
    iot_id bigint NOT NULL
);


ALTER TABLE public.logisticpartner_iot OWNER TO webadmin;

--
-- Name: logisticpartner_logistic; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.logisticpartner_logistic (
    logisticpartner_id bigint NOT NULL,
    logistics_id bigint NOT NULL
);


ALTER TABLE public.logisticpartner_logistic OWNER TO webadmin;

--
-- Name: logisticpartner_package; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.logisticpartner_package (
    logisticpartner_id bigint NOT NULL,
    packages_id bigint NOT NULL
);


ALTER TABLE public.logisticpartner_package OWNER TO webadmin;

--
-- Name: package; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.package (
    id bigint NOT NULL,
    createdate timestamp without time zone,
    deletedate timestamp without time zone,
    updatedate timestamp without time zone,
    userid character varying(255),
    packagenumber bigint,
    packagestate integer,
    quantity bigint
);


ALTER TABLE public.package OWNER TO webadmin;

--
-- Name: package_id_seq; Type: SEQUENCE; Schema: public; Owner: webadmin
--

CREATE SEQUENCE public.package_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.package_id_seq OWNER TO webadmin;

--
-- Name: package_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: webadmin
--

ALTER SEQUENCE public.package_id_seq OWNED BY public.package.id;


--
-- Name: package_vaccination; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.package_vaccination (
    apackage_id bigint,
    id bigint NOT NULL
);


ALTER TABLE public.package_vaccination OWNER TO webadmin;

--
-- Name: package_vaccinationact; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.package_vaccinationact (
    apackage_id bigint,
    id bigint NOT NULL
);


ALTER TABLE public.package_vaccinationact OWNER TO webadmin;

--
-- Name: person; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.person (
    id bigint NOT NULL,
    createdate timestamp without time zone,
    deletedate timestamp without time zone,
    updatedate timestamp without time zone,
    userid character varying(255),
    birthday date,
    ci character varying(255),
    email character varying(255),
    iduruguay character varying(255),
    lastname character varying(255),
    name character varying(255),
    secondlastname character varying(255),
    sex integer,
    surname character varying(255)
);


ALTER TABLE public.person OWNER TO webadmin;

--
-- Name: person_id_seq; Type: SEQUENCE; Schema: public; Owner: webadmin
--

CREATE SEQUENCE public.person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.person_id_seq OWNER TO webadmin;

--
-- Name: person_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: webadmin
--

ALTER SEQUENCE public.person_id_seq OWNED BY public.person.id;


--
-- Name: person_roles; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.person_roles (
    person_id bigint,
    id bigint NOT NULL
);


ALTER TABLE public.person_roles OWNER TO webadmin;

--
-- Name: reservation; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.reservation (
    id bigint NOT NULL,
    createdate timestamp without time zone,
    deletedate timestamp without time zone,
    updatedate timestamp without time zone,
    userid character varying(255),
    date character varying(255),
    doses integer NOT NULL,
    reservationstate integer,
    "time" character varying(255)
);


ALTER TABLE public.reservation OWNER TO webadmin;

--
-- Name: reservation_id_seq; Type: SEQUENCE; Schema: public; Owner: webadmin
--

CREATE SEQUENCE public.reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.reservation_id_seq OWNER TO webadmin;

--
-- Name: reservation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: webadmin
--

ALTER SEQUENCE public.reservation_id_seq OWNED BY public.reservation.id;


--
-- Name: restriction; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.restriction (
    id bigint NOT NULL,
    createdate timestamp without time zone,
    deletedate timestamp without time zone,
    updatedate timestamp without time zone,
    userid character varying(255),
    description character varying(255),
    elementname character varying(255),
    logicoperator integer,
    value integer NOT NULL,
    datasource_id bigint
);


ALTER TABLE public.restriction OWNER TO webadmin;

--
-- Name: restriction_id_seq; Type: SEQUENCE; Schema: public; Owner: webadmin
--

CREATE SEQUENCE public.restriction_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.restriction_id_seq OWNER TO webadmin;

--
-- Name: restriction_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: webadmin
--

ALTER SEQUENCE public.restriction_id_seq OWNED BY public.restriction.id;


--
-- Name: role; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.role (
    dtype character varying(31) NOT NULL,
    id bigint NOT NULL,
    createdate timestamp without time zone,
    deletedate timestamp without time zone,
    updatedate timestamp without time zone,
    userid character varying(255),
    name character varying(255),
    token character varying(255)
);


ALTER TABLE public.role OWNER TO webadmin;

--
-- Name: role_id_seq; Type: SEQUENCE; Schema: public; Owner: webadmin
--

CREATE SEQUENCE public.role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.role_id_seq OWNER TO webadmin;

--
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: webadmin
--

ALTER SEQUENCE public.role_id_seq OWNED BY public.role.id;


--
-- Name: schedule; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.schedule (
    id bigint NOT NULL,
    createdate timestamp without time zone,
    deletedate timestamp without time zone,
    updatedate timestamp without time zone,
    userid character varying(255),
    enddate character varying(255),
    endtimedaily character varying(255),
    fraction integer NOT NULL,
    saturday boolean NOT NULL,
    startdate character varying(255),
    starttimedaily character varying(255),
    sunday boolean NOT NULL
);


ALTER TABLE public.schedule OWNER TO webadmin;

--
-- Name: schedule_id_seq; Type: SEQUENCE; Schema: public; Owner: webadmin
--

CREATE SEQUENCE public.schedule_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.schedule_id_seq OWNER TO webadmin;

--
-- Name: schedule_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: webadmin
--

ALTER SEQUENCE public.schedule_id_seq OWNED BY public.schedule.id;


--
-- Name: schedule_reservation; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.schedule_reservation (
    schedule_id bigint,
    id bigint NOT NULL
);


ALTER TABLE public.schedule_reservation OWNER TO webadmin;

--
-- Name: vaccination; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.vaccination (
    id bigint NOT NULL,
    createdate timestamp without time zone,
    deletedate timestamp without time zone,
    updatedate timestamp without time zone,
    userid character varying(255),
    date date,
    state integer,
    vaccinationcenter_id bigint
);


ALTER TABLE public.vaccination OWNER TO webadmin;

--
-- Name: vaccination_id_seq; Type: SEQUENCE; Schema: public; Owner: webadmin
--

CREATE SEQUENCE public.vaccination_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.vaccination_id_seq OWNER TO webadmin;

--
-- Name: vaccination_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: webadmin
--

ALTER SEQUENCE public.vaccination_id_seq OWNED BY public.vaccination.id;


--
-- Name: vaccinationact; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.vaccinationact (
    id bigint NOT NULL,
    createdate timestamp without time zone,
    deletedate timestamp without time zone,
    updatedate timestamp without time zone,
    userid character varying(255),
    observation character varying(255)
);


ALTER TABLE public.vaccinationact OWNER TO webadmin;

--
-- Name: vaccinationact_id_seq; Type: SEQUENCE; Schema: public; Owner: webadmin
--

CREATE SEQUENCE public.vaccinationact_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.vaccinationact_id_seq OWNER TO webadmin;

--
-- Name: vaccinationact_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: webadmin
--

ALTER SEQUENCE public.vaccinationact_id_seq OWNED BY public.vaccinationact.id;


--
-- Name: vaccinationcenter; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.vaccinationcenter (
    id bigint NOT NULL,
    createdate timestamp without time zone,
    deletedate timestamp without time zone,
    updatedate timestamp without time zone,
    userid character varying(255),
    latitude character varying(255),
    location character varying(255),
    longitude character varying(255),
    name character varying(255),
    password character varying(255)
);


ALTER TABLE public.vaccinationcenter OWNER TO webadmin;

--
-- Name: vaccinationcenter_id_seq; Type: SEQUENCE; Schema: public; Owner: webadmin
--

CREATE SEQUENCE public.vaccinationcenter_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.vaccinationcenter_id_seq OWNER TO webadmin;

--
-- Name: vaccinationcenter_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: webadmin
--

ALTER SEQUENCE public.vaccinationcenter_id_seq OWNED BY public.vaccinationcenter.id;


--
-- Name: vaccinationcenter_package; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.vaccinationcenter_package (
    vaccinationcenter_id bigint,
    id bigint NOT NULL
);


ALTER TABLE public.vaccinationcenter_package OWNER TO webadmin;

--
-- Name: vaccinationcenter_reservation; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.vaccinationcenter_reservation (
    vaccinationcenter_id bigint,
    id bigint NOT NULL
);


ALTER TABLE public.vaccinationcenter_reservation OWNER TO webadmin;

--
-- Name: vaccinationcenter_schedule; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.vaccinationcenter_schedule (
    vaccinationcenter_id bigint,
    id bigint NOT NULL
);


ALTER TABLE public.vaccinationcenter_schedule OWNER TO webadmin;

--
-- Name: vaccinationcenter_vaccination; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.vaccinationcenter_vaccination (
    vaccinationcenter_id bigint NOT NULL,
    vaccinations_id bigint NOT NULL
);


ALTER TABLE public.vaccinationcenter_vaccination OWNER TO webadmin;

--
-- Name: vaccinationcenter_vaccinationpost; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.vaccinationcenter_vaccinationpost (
    vaccinationcenter_id bigint,
    id bigint NOT NULL
);


ALTER TABLE public.vaccinationcenter_vaccinationpost OWNER TO webadmin;

--
-- Name: vaccinationplan; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.vaccinationplan (
    id bigint NOT NULL,
    createdate timestamp without time zone,
    deletedate timestamp without time zone,
    updatedate timestamp without time zone,
    userid character varying(255),
    enddate character varying(255),
    name character varying(255),
    startdate character varying(255),
    vaccinequantity integer NOT NULL,
    vaccine_id bigint
);


ALTER TABLE public.vaccinationplan OWNER TO webadmin;

--
-- Name: vaccinationplan_id_seq; Type: SEQUENCE; Schema: public; Owner: webadmin
--

CREATE SEQUENCE public.vaccinationplan_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.vaccinationplan_id_seq OWNER TO webadmin;

--
-- Name: vaccinationplan_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: webadmin
--

ALTER SEQUENCE public.vaccinationplan_id_seq OWNED BY public.vaccinationplan.id;


--
-- Name: vaccinationplan_package; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.vaccinationplan_package (
    vaccinationplan_id bigint,
    id bigint NOT NULL
);


ALTER TABLE public.vaccinationplan_package OWNER TO webadmin;

--
-- Name: vaccinationplan_restriction; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.vaccinationplan_restriction (
    fk_vaccinationplan bigint NOT NULL,
    fk_restriction bigint NOT NULL
);


ALTER TABLE public.vaccinationplan_restriction OWNER TO webadmin;

--
-- Name: vaccinationplan_schedule; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.vaccinationplan_schedule (
    vaccinationplan_id bigint,
    id bigint NOT NULL
);


ALTER TABLE public.vaccinationplan_schedule OWNER TO webadmin;

--
-- Name: vaccinationplan_vaccinationcenter; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.vaccinationplan_vaccinationcenter (
    fk_vaccinationplan bigint NOT NULL,
    fk_vaccinationcenter bigint NOT NULL
);


ALTER TABLE public.vaccinationplan_vaccinationcenter OWNER TO webadmin;

--
-- Name: vaccinationpost; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.vaccinationpost (
    id bigint NOT NULL,
    createdate timestamp without time zone,
    deletedate timestamp without time zone,
    updatedate timestamp without time zone,
    userid character varying(255),
    name character varying(255),
    observation character varying(255)
);


ALTER TABLE public.vaccinationpost OWNER TO webadmin;

--
-- Name: vaccinationpost_id_seq; Type: SEQUENCE; Schema: public; Owner: webadmin
--

CREATE SEQUENCE public.vaccinationpost_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.vaccinationpost_id_seq OWNER TO webadmin;

--
-- Name: vaccinationpost_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: webadmin
--

ALTER SEQUENCE public.vaccinationpost_id_seq OWNED BY public.vaccinationpost.id;


--
-- Name: vaccinationpost_reservation; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.vaccinationpost_reservation (
    vaccinationpost_id bigint,
    id bigint NOT NULL
);


ALTER TABLE public.vaccinationpost_reservation OWNER TO webadmin;

--
-- Name: vaccinationpost_vaccinationact; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.vaccinationpost_vaccinationact (
    vaccinationpost_id bigint,
    id bigint NOT NULL
);


ALTER TABLE public.vaccinationpost_vaccinationact OWNER TO webadmin;

--
-- Name: vaccine; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.vaccine (
    id bigint NOT NULL,
    createdate timestamp without time zone,
    deletedate timestamp without time zone,
    updatedate timestamp without time zone,
    userid character varying(255),
    dosequantity integer NOT NULL,
    inmunity integer NOT NULL,
    monthquantity integer NOT NULL,
    name character varying(255),
    temperature real NOT NULL
);


ALTER TABLE public.vaccine OWNER TO webadmin;

--
-- Name: vaccine_batch; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.vaccine_batch (
    vaccine_id bigint,
    id bigint NOT NULL
);


ALTER TABLE public.vaccine_batch OWNER TO webadmin;

--
-- Name: vaccine_disease; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.vaccine_disease (
    disease_id bigint,
    id bigint NOT NULL
);


ALTER TABLE public.vaccine_disease OWNER TO webadmin;

--
-- Name: vaccine_id_seq; Type: SEQUENCE; Schema: public; Owner: webadmin
--

CREATE SEQUENCE public.vaccine_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.vaccine_id_seq OWNER TO webadmin;

--
-- Name: vaccine_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: webadmin
--

ALTER SEQUENCE public.vaccine_id_seq OWNED BY public.vaccine.id;


--
-- Name: vaccine_laboratory; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.vaccine_laboratory (
    laboratory_id bigint,
    id bigint NOT NULL
);


ALTER TABLE public.vaccine_laboratory OWNER TO webadmin;

--
-- Name: vaccine_restriction; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE public.vaccine_restriction (
    fk_vaccine bigint NOT NULL,
    fk_restriction bigint NOT NULL
);


ALTER TABLE public.vaccine_restriction OWNER TO webadmin;

--
-- Name: assignment id; Type: DEFAULT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.assignment ALTER COLUMN id SET DEFAULT nextval('public.assignment_id_seq'::regclass);


--
-- Name: batch id; Type: DEFAULT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.batch ALTER COLUMN id SET DEFAULT nextval('public.batch_id_seq'::regclass);


--
-- Name: datasource id; Type: DEFAULT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.datasource ALTER COLUMN id SET DEFAULT nextval('public.datasource_id_seq'::regclass);


--
-- Name: disease id; Type: DEFAULT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.disease ALTER COLUMN id SET DEFAULT nextval('public.disease_id_seq'::regclass);


--
-- Name: iot id; Type: DEFAULT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.iot ALTER COLUMN id SET DEFAULT nextval('public.iot_id_seq'::regclass);


--
-- Name: laboratory id; Type: DEFAULT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.laboratory ALTER COLUMN id SET DEFAULT nextval('public.laboratory_id_seq'::regclass);


--
-- Name: logistic id; Type: DEFAULT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.logistic ALTER COLUMN id SET DEFAULT nextval('public.logistic_id_seq'::regclass);


--
-- Name: logisticpartner id; Type: DEFAULT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.logisticpartner ALTER COLUMN id SET DEFAULT nextval('public.logisticpartner_id_seq'::regclass);


--
-- Name: package id; Type: DEFAULT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.package ALTER COLUMN id SET DEFAULT nextval('public.package_id_seq'::regclass);


--
-- Name: person id; Type: DEFAULT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.person ALTER COLUMN id SET DEFAULT nextval('public.person_id_seq'::regclass);


--
-- Name: reservation id; Type: DEFAULT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.reservation ALTER COLUMN id SET DEFAULT nextval('public.reservation_id_seq'::regclass);


--
-- Name: restriction id; Type: DEFAULT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.restriction ALTER COLUMN id SET DEFAULT nextval('public.restriction_id_seq'::regclass);


--
-- Name: role id; Type: DEFAULT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.role ALTER COLUMN id SET DEFAULT nextval('public.role_id_seq'::regclass);


--
-- Name: schedule id; Type: DEFAULT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.schedule ALTER COLUMN id SET DEFAULT nextval('public.schedule_id_seq'::regclass);


--
-- Name: vaccination id; Type: DEFAULT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccination ALTER COLUMN id SET DEFAULT nextval('public.vaccination_id_seq'::regclass);


--
-- Name: vaccinationact id; Type: DEFAULT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationact ALTER COLUMN id SET DEFAULT nextval('public.vaccinationact_id_seq'::regclass);


--
-- Name: vaccinationcenter id; Type: DEFAULT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationcenter ALTER COLUMN id SET DEFAULT nextval('public.vaccinationcenter_id_seq'::regclass);


--
-- Name: vaccinationplan id; Type: DEFAULT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationplan ALTER COLUMN id SET DEFAULT nextval('public.vaccinationplan_id_seq'::regclass);


--
-- Name: vaccinationpost id; Type: DEFAULT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationpost ALTER COLUMN id SET DEFAULT nextval('public.vaccinationpost_id_seq'::regclass);


--
-- Name: vaccine id; Type: DEFAULT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccine ALTER COLUMN id SET DEFAULT nextval('public.vaccine_id_seq'::regclass);


--
-- Name: assignment assignment_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.assignment
    ADD CONSTRAINT assignment_pkey PRIMARY KEY (id);


--
-- Name: authority_schedule authority_schedule_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.authority_schedule
    ADD CONSTRAINT authority_schedule_pkey PRIMARY KEY (id);


--
-- Name: authority_vaccinationplan authority_vaccinationplan_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.authority_vaccinationplan
    ADD CONSTRAINT authority_vaccinationplan_pkey PRIMARY KEY (id);


--
-- Name: batch_package batch_package_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.batch_package
    ADD CONSTRAINT batch_package_pkey PRIMARY KEY (id);


--
-- Name: batch batch_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.batch
    ADD CONSTRAINT batch_pkey PRIMARY KEY (id);


--
-- Name: citizen_reservation citizen_reservation_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.citizen_reservation
    ADD CONSTRAINT citizen_reservation_pkey PRIMARY KEY (id);


--
-- Name: citizen_vaccinationact citizen_vaccinationact_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.citizen_vaccinationact
    ADD CONSTRAINT citizen_vaccinationact_pkey PRIMARY KEY (id);


--
-- Name: datasource datasource_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.datasource
    ADD CONSTRAINT datasource_pkey PRIMARY KEY (id);


--
-- Name: disease disease_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.disease
    ADD CONSTRAINT disease_pkey PRIMARY KEY (id);


--
-- Name: iot iot_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.iot
    ADD CONSTRAINT iot_pkey PRIMARY KEY (id);


--
-- Name: laboratory laboratory_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.laboratory
    ADD CONSTRAINT laboratory_pkey PRIMARY KEY (id);


--
-- Name: logistic logistic_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.logistic
    ADD CONSTRAINT logistic_pkey PRIMARY KEY (id);


--
-- Name: logisticpartner logisticpartner_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.logisticpartner
    ADD CONSTRAINT logisticpartner_pkey PRIMARY KEY (id);


--
-- Name: package package_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.package
    ADD CONSTRAINT package_pkey PRIMARY KEY (id);


--
-- Name: package_vaccination package_vaccination_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.package_vaccination
    ADD CONSTRAINT package_vaccination_pkey PRIMARY KEY (id);


--
-- Name: package_vaccinationact package_vaccinationact_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.package_vaccinationact
    ADD CONSTRAINT package_vaccinationact_pkey PRIMARY KEY (id);


--
-- Name: person person_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);


--
-- Name: person_roles person_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.person_roles
    ADD CONSTRAINT person_roles_pkey PRIMARY KEY (id);


--
-- Name: reservation reservation_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (id);


--
-- Name: restriction restriction_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.restriction
    ADD CONSTRAINT restriction_pkey PRIMARY KEY (id);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: schedule schedule_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.schedule
    ADD CONSTRAINT schedule_pkey PRIMARY KEY (id);


--
-- Name: schedule_reservation schedule_reservation_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.schedule_reservation
    ADD CONSTRAINT schedule_reservation_pkey PRIMARY KEY (id);


--
-- Name: vaccinationcenter_vaccination uk_2at6fxtbtn081got79w4yr4ph; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationcenter_vaccination
    ADD CONSTRAINT uk_2at6fxtbtn081got79w4yr4ph UNIQUE (vaccinations_id);


--
-- Name: logisticpartner_logistic uk_ndvxgg69shj07hyg4mapvbami; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.logisticpartner_logistic
    ADD CONSTRAINT uk_ndvxgg69shj07hyg4mapvbami UNIQUE (logistics_id);


--
-- Name: logisticpartner_iot uk_pjr94gchu4s3f69m92h694800; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.logisticpartner_iot
    ADD CONSTRAINT uk_pjr94gchu4s3f69m92h694800 UNIQUE (iot_id);


--
-- Name: logisticpartner_package uk_tc4kffub0xew6s4p6yu0oo4gw; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.logisticpartner_package
    ADD CONSTRAINT uk_tc4kffub0xew6s4p6yu0oo4gw UNIQUE (packages_id);


--
-- Name: vaccination vaccination_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccination
    ADD CONSTRAINT vaccination_pkey PRIMARY KEY (id);


--
-- Name: vaccinationact vaccinationact_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationact
    ADD CONSTRAINT vaccinationact_pkey PRIMARY KEY (id);


--
-- Name: vaccinationcenter_package vaccinationcenter_package_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationcenter_package
    ADD CONSTRAINT vaccinationcenter_package_pkey PRIMARY KEY (id);


--
-- Name: vaccinationcenter vaccinationcenter_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationcenter
    ADD CONSTRAINT vaccinationcenter_pkey PRIMARY KEY (id);


--
-- Name: vaccinationcenter_reservation vaccinationcenter_reservation_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationcenter_reservation
    ADD CONSTRAINT vaccinationcenter_reservation_pkey PRIMARY KEY (id);


--
-- Name: vaccinationcenter_schedule vaccinationcenter_schedule_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationcenter_schedule
    ADD CONSTRAINT vaccinationcenter_schedule_pkey PRIMARY KEY (id);


--
-- Name: vaccinationcenter_vaccinationpost vaccinationcenter_vaccinationpost_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationcenter_vaccinationpost
    ADD CONSTRAINT vaccinationcenter_vaccinationpost_pkey PRIMARY KEY (id);


--
-- Name: vaccinationplan_package vaccinationplan_package_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationplan_package
    ADD CONSTRAINT vaccinationplan_package_pkey PRIMARY KEY (id);


--
-- Name: vaccinationplan vaccinationplan_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationplan
    ADD CONSTRAINT vaccinationplan_pkey PRIMARY KEY (id);


--
-- Name: vaccinationplan_schedule vaccinationplan_schedule_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationplan_schedule
    ADD CONSTRAINT vaccinationplan_schedule_pkey PRIMARY KEY (id);


--
-- Name: vaccinationpost vaccinationpost_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationpost
    ADD CONSTRAINT vaccinationpost_pkey PRIMARY KEY (id);


--
-- Name: vaccinationpost_reservation vaccinationpost_reservation_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationpost_reservation
    ADD CONSTRAINT vaccinationpost_reservation_pkey PRIMARY KEY (id);


--
-- Name: vaccinationpost_vaccinationact vaccinationpost_vaccinationact_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationpost_vaccinationact
    ADD CONSTRAINT vaccinationpost_vaccinationact_pkey PRIMARY KEY (id);


--
-- Name: vaccine_batch vaccine_batch_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccine_batch
    ADD CONSTRAINT vaccine_batch_pkey PRIMARY KEY (id);


--
-- Name: vaccine_disease vaccine_disease_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccine_disease
    ADD CONSTRAINT vaccine_disease_pkey PRIMARY KEY (id);


--
-- Name: vaccine_laboratory vaccine_laboratory_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccine_laboratory
    ADD CONSTRAINT vaccine_laboratory_pkey PRIMARY KEY (id);


--
-- Name: vaccine vaccine_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccine
    ADD CONSTRAINT vaccine_pkey PRIMARY KEY (id);


--
-- Name: vaccine_restriction fk1wrrpbus8gu322kgo3ybg5hqs; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccine_restriction
    ADD CONSTRAINT fk1wrrpbus8gu322kgo3ybg5hqs FOREIGN KEY (fk_restriction) REFERENCES public.restriction(id);


--
-- Name: vaccinationplan_package fk2bsme33abpshas1s08rvyo7gd; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationplan_package
    ADD CONSTRAINT fk2bsme33abpshas1s08rvyo7gd FOREIGN KEY (id) REFERENCES public.package(id);


--
-- Name: citizen_reservation fk2evpwukhqyvar9te0blrca0gd; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.citizen_reservation
    ADD CONSTRAINT fk2evpwukhqyvar9te0blrca0gd FOREIGN KEY (citizen_id) REFERENCES public.role(id);


--
-- Name: logisticpartner_iot fk2fcyf3p57hos73njydkw5u5xh; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.logisticpartner_iot
    ADD CONSTRAINT fk2fcyf3p57hos73njydkw5u5xh FOREIGN KEY (iot_id) REFERENCES public.iot(id);


--
-- Name: vaccinationpost_vaccinationact fk2t3co4271qk7ltcfi5ynh85ld; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationpost_vaccinationact
    ADD CONSTRAINT fk2t3co4271qk7ltcfi5ynh85ld FOREIGN KEY (vaccinationpost_id) REFERENCES public.vaccinationpost(id);


--
-- Name: schedule_reservation fk512paysn8a455qodr1o5bp0e; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.schedule_reservation
    ADD CONSTRAINT fk512paysn8a455qodr1o5bp0e FOREIGN KEY (id) REFERENCES public.reservation(id);


--
-- Name: citizen_reservation fk5o3lsk3ijbpukcs9w044cqgb1; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.citizen_reservation
    ADD CONSTRAINT fk5o3lsk3ijbpukcs9w044cqgb1 FOREIGN KEY (id) REFERENCES public.reservation(id);


--
-- Name: person_roles fk5x7kka4in4l0nyy9odw627mki; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.person_roles
    ADD CONSTRAINT fk5x7kka4in4l0nyy9odw627mki FOREIGN KEY (person_id) REFERENCES public.person(id);


--
-- Name: iot fk5ygc3b4b4n4y4yt2dp9v7rskh; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.iot
    ADD CONSTRAINT fk5ygc3b4b4n4y4yt2dp9v7rskh FOREIGN KEY (logisticpartner_id) REFERENCES public.logisticpartner(id);


--
-- Name: vaccinationplan_vaccinationcenter fk60f6wnu98pshdfnmuwrpy86j0; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationplan_vaccinationcenter
    ADD CONSTRAINT fk60f6wnu98pshdfnmuwrpy86j0 FOREIGN KEY (fk_vaccinationcenter) REFERENCES public.vaccinationcenter(id);


--
-- Name: schedule_reservation fk7dxy0oapri2oou0yn74o7ykg4; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.schedule_reservation
    ADD CONSTRAINT fk7dxy0oapri2oou0yn74o7ykg4 FOREIGN KEY (schedule_id) REFERENCES public.schedule(id);


--
-- Name: vaccinationpost_vaccinationact fk7npl9x9r44r9brt9wl3dmrfss; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationpost_vaccinationact
    ADD CONSTRAINT fk7npl9x9r44r9brt9wl3dmrfss FOREIGN KEY (id) REFERENCES public.vaccinationact(id);


--
-- Name: vaccinationcenter_package fk86wgxaqv2hevn2xtnv386e87v; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationcenter_package
    ADD CONSTRAINT fk86wgxaqv2hevn2xtnv386e87v FOREIGN KEY (vaccinationcenter_id) REFERENCES public.vaccinationcenter(id);


--
-- Name: authority_schedule fk87w0mt32sy9i675oo8da5ktu9; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.authority_schedule
    ADD CONSTRAINT fk87w0mt32sy9i675oo8da5ktu9 FOREIGN KEY (authority_id) REFERENCES public.role(id);


--
-- Name: package_vaccination fk8c1yslqwwwjdmw0p0bday79ah; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.package_vaccination
    ADD CONSTRAINT fk8c1yslqwwwjdmw0p0bday79ah FOREIGN KEY (apackage_id) REFERENCES public.package(id);


--
-- Name: citizen_vaccinationact fk9dqsiynrso4qa47m3yg1c4pta; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.citizen_vaccinationact
    ADD CONSTRAINT fk9dqsiynrso4qa47m3yg1c4pta FOREIGN KEY (id) REFERENCES public.vaccinationact(id);


--
-- Name: vaccine_disease fk9tlo3lqh6huohbeq34sjvvkww; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccine_disease
    ADD CONSTRAINT fk9tlo3lqh6huohbeq34sjvvkww FOREIGN KEY (id) REFERENCES public.vaccine(id);


--
-- Name: restriction fkag5a2wbssmkdojjs2byf6dct5; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.restriction
    ADD CONSTRAINT fkag5a2wbssmkdojjs2byf6dct5 FOREIGN KEY (datasource_id) REFERENCES public.datasource(id);


--
-- Name: vaccinationcenter_reservation fkalc8olrpney02qeg8455mj318; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationcenter_reservation
    ADD CONSTRAINT fkalc8olrpney02qeg8455mj318 FOREIGN KEY (vaccinationcenter_id) REFERENCES public.vaccinationcenter(id);


--
-- Name: logisticpartner_logistic fkaqlhv4r5ci59daa4vptuimpy3; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.logisticpartner_logistic
    ADD CONSTRAINT fkaqlhv4r5ci59daa4vptuimpy3 FOREIGN KEY (logisticpartner_id) REFERENCES public.logisticpartner(id);


--
-- Name: vaccinationplan_package fkbwi139lgu2hwxscmmsgwiq16; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationplan_package
    ADD CONSTRAINT fkbwi139lgu2hwxscmmsgwiq16 FOREIGN KEY (vaccinationplan_id) REFERENCES public.vaccinationplan(id);


--
-- Name: vaccinationcenter_vaccinationpost fkdu6kyuwg87a9i11esj3bsm09k; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationcenter_vaccinationpost
    ADD CONSTRAINT fkdu6kyuwg87a9i11esj3bsm09k FOREIGN KEY (vaccinationcenter_id) REFERENCES public.vaccinationcenter(id);


--
-- Name: package_vaccination fkdughmo2c6a9k0aju6yul201dm; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.package_vaccination
    ADD CONSTRAINT fkdughmo2c6a9k0aju6yul201dm FOREIGN KEY (id) REFERENCES public.vaccination(id);


--
-- Name: vaccine_batch fkdwrpl5wspc86cnr4qchs8c0jd; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccine_batch
    ADD CONSTRAINT fkdwrpl5wspc86cnr4qchs8c0jd FOREIGN KEY (id) REFERENCES public.batch(id);


--
-- Name: package_vaccinationact fke5jch1ibuujbwkoly3xvpb76c; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.package_vaccinationact
    ADD CONSTRAINT fke5jch1ibuujbwkoly3xvpb76c FOREIGN KEY (id) REFERENCES public.vaccinationact(id);


--
-- Name: vaccinationcenter_vaccination fkeh111erk93n9xvfdbkocuwyd7; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationcenter_vaccination
    ADD CONSTRAINT fkeh111erk93n9xvfdbkocuwyd7 FOREIGN KEY (vaccinations_id) REFERENCES public.vaccination(id);


--
-- Name: vaccinationplan fkerfdxoea36a8rkd5meqnjd5di; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationplan
    ADD CONSTRAINT fkerfdxoea36a8rkd5meqnjd5di FOREIGN KEY (vaccine_id) REFERENCES public.vaccine(id);


--
-- Name: vaccinationplan_schedule fkfes7alt3x6g1f68u1a8fmsvmd; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationplan_schedule
    ADD CONSTRAINT fkfes7alt3x6g1f68u1a8fmsvmd FOREIGN KEY (vaccinationplan_id) REFERENCES public.vaccinationplan(id);


--
-- Name: vaccine_batch fkfu6wc43ir2138dmhudgf8svlv; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccine_batch
    ADD CONSTRAINT fkfu6wc43ir2138dmhudgf8svlv FOREIGN KEY (vaccine_id) REFERENCES public.vaccine(id);


--
-- Name: logisticpartner_iot fkgm69to0u9mmen5difevs2mg4s; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.logisticpartner_iot
    ADD CONSTRAINT fkgm69to0u9mmen5difevs2mg4s FOREIGN KEY (logisticpartner_id) REFERENCES public.logisticpartner(id);


--
-- Name: vaccine_disease fkgpofwbqv6qkysp8o8impluqlg; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccine_disease
    ADD CONSTRAINT fkgpofwbqv6qkysp8o8impluqlg FOREIGN KEY (disease_id) REFERENCES public.disease(id);


--
-- Name: vaccination fkgtips2qwaxlnofh7hn2et5psd; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccination
    ADD CONSTRAINT fkgtips2qwaxlnofh7hn2et5psd FOREIGN KEY (vaccinationcenter_id) REFERENCES public.vaccinationcenter(id);


--
-- Name: batch_package fkgwlgls0j8b1l6q0l5nb1kvvq1; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.batch_package
    ADD CONSTRAINT fkgwlgls0j8b1l6q0l5nb1kvvq1 FOREIGN KEY (batch_id) REFERENCES public.batch(id);


--
-- Name: logisticpartner_logistic fkh4pd1ql453xdpxq7806dhhtjx; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.logisticpartner_logistic
    ADD CONSTRAINT fkh4pd1ql453xdpxq7806dhhtjx FOREIGN KEY (logistics_id) REFERENCES public.logistic(id);


--
-- Name: authority_schedule fki2y4sxag0iftkcnrid4rmolum; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.authority_schedule
    ADD CONSTRAINT fki2y4sxag0iftkcnrid4rmolum FOREIGN KEY (id) REFERENCES public.schedule(id);


--
-- Name: package_vaccinationact fki9vhoxvfvseumpt4gd4col0lo; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.package_vaccinationact
    ADD CONSTRAINT fki9vhoxvfvseumpt4gd4col0lo FOREIGN KEY (apackage_id) REFERENCES public.package(id);


--
-- Name: vaccinationpost_reservation fkiab5ysbvaryt4sjam39s50yo5; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationpost_reservation
    ADD CONSTRAINT fkiab5ysbvaryt4sjam39s50yo5 FOREIGN KEY (vaccinationpost_id) REFERENCES public.vaccinationpost(id);


--
-- Name: vaccinationpost_reservation fkiggk9ilyp6jqbi2h3087b8iin; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationpost_reservation
    ADD CONSTRAINT fkiggk9ilyp6jqbi2h3087b8iin FOREIGN KEY (id) REFERENCES public.reservation(id);


--
-- Name: authority_vaccinationplan fkiypduhu2kvylscum7dmfgw8pe; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.authority_vaccinationplan
    ADD CONSTRAINT fkiypduhu2kvylscum7dmfgw8pe FOREIGN KEY (authority_id) REFERENCES public.role(id);


--
-- Name: vaccinationcenter_reservation fkj2sksiieeecxhtc8c0ht87dad; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationcenter_reservation
    ADD CONSTRAINT fkj2sksiieeecxhtc8c0ht87dad FOREIGN KEY (id) REFERENCES public.reservation(id);


--
-- Name: datasource fkjb8a7wvfc4qwgg6jedyb34v8a; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.datasource
    ADD CONSTRAINT fkjb8a7wvfc4qwgg6jedyb34v8a FOREIGN KEY (restriction_id) REFERENCES public.restriction(id);


--
-- Name: vaccinationcenter_schedule fkjxqjhjmy3a0gnugkuag55xam5; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationcenter_schedule
    ADD CONSTRAINT fkjxqjhjmy3a0gnugkuag55xam5 FOREIGN KEY (id) REFERENCES public.schedule(id);


--
-- Name: vaccinationplan_vaccinationcenter fkke3dp8sk0rq7g24i21xuvnvr1; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationplan_vaccinationcenter
    ADD CONSTRAINT fkke3dp8sk0rq7g24i21xuvnvr1 FOREIGN KEY (fk_vaccinationplan) REFERENCES public.vaccinationplan(id);


--
-- Name: vaccinationcenter_vaccinationpost fkklbobhpy4w6s14cqedgieoob2; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationcenter_vaccinationpost
    ADD CONSTRAINT fkklbobhpy4w6s14cqedgieoob2 FOREIGN KEY (id) REFERENCES public.vaccinationpost(id);


--
-- Name: logisticpartner_package fklbl962oseigm4h1o81drxtq46; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.logisticpartner_package
    ADD CONSTRAINT fklbl962oseigm4h1o81drxtq46 FOREIGN KEY (packages_id) REFERENCES public.package(id);


--
-- Name: batch_package fklx12sq0wi682iwybbogk3yp2w; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.batch_package
    ADD CONSTRAINT fklx12sq0wi682iwybbogk3yp2w FOREIGN KEY (id) REFERENCES public.package(id);


--
-- Name: vaccine_laboratory fkmpeatduvi4rhlipr0hp6bgjo3; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccine_laboratory
    ADD CONSTRAINT fkmpeatduvi4rhlipr0hp6bgjo3 FOREIGN KEY (id) REFERENCES public.vaccine(id);


--
-- Name: vaccinationplan_restriction fknbj1l8s21112hnd0nvyq5qfgt; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationplan_restriction
    ADD CONSTRAINT fknbj1l8s21112hnd0nvyq5qfgt FOREIGN KEY (fk_vaccinationplan) REFERENCES public.vaccinationplan(id);


--
-- Name: vaccinationplan_restriction fknd1dgc1a9fh96f9o7lrexkw0d; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationplan_restriction
    ADD CONSTRAINT fknd1dgc1a9fh96f9o7lrexkw0d FOREIGN KEY (fk_restriction) REFERENCES public.restriction(id);


--
-- Name: vaccinationcenter_schedule fkngy628v2hsg12xypdor61ew62; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationcenter_schedule
    ADD CONSTRAINT fkngy628v2hsg12xypdor61ew62 FOREIGN KEY (vaccinationcenter_id) REFERENCES public.vaccinationcenter(id);


--
-- Name: vaccine_laboratory fknmm2reom40rsdyp41aby6yys6; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccine_laboratory
    ADD CONSTRAINT fknmm2reom40rsdyp41aby6yys6 FOREIGN KEY (laboratory_id) REFERENCES public.laboratory(id);


--
-- Name: assignment fknpbofnyhi1hk3v3wm8kw4gsvi; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.assignment
    ADD CONSTRAINT fknpbofnyhi1hk3v3wm8kw4gsvi FOREIGN KEY (id_schedule) REFERENCES public.schedule(id);


--
-- Name: person_roles fkoaars7tyhrwv8o2ougdf4laln; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.person_roles
    ADD CONSTRAINT fkoaars7tyhrwv8o2ougdf4laln FOREIGN KEY (id) REFERENCES public.role(id);


--
-- Name: assignment fkos8wb398nup1hy1ky57k3nc54; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.assignment
    ADD CONSTRAINT fkos8wb398nup1hy1ky57k3nc54 FOREIGN KEY (id_vpost) REFERENCES public.vaccinationpost(id);


--
-- Name: vaccinationplan_schedule fkqx16ks8hapfd9v5cq8bj5w983; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationplan_schedule
    ADD CONSTRAINT fkqx16ks8hapfd9v5cq8bj5w983 FOREIGN KEY (id) REFERENCES public.schedule(id);


--
-- Name: logistic fkr8vt3sr86yws4ps9v54b5ipot; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.logistic
    ADD CONSTRAINT fkr8vt3sr86yws4ps9v54b5ipot FOREIGN KEY (logisticpartner_id) REFERENCES public.logisticpartner(id);


--
-- Name: citizen_vaccinationact fkrk35vmo8drb74nywwpiafje0r; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.citizen_vaccinationact
    ADD CONSTRAINT fkrk35vmo8drb74nywwpiafje0r FOREIGN KEY (citizen_id) REFERENCES public.role(id);


--
-- Name: assignment fkrsme11kiv44bxi68shu9hkk2f; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.assignment
    ADD CONSTRAINT fkrsme11kiv44bxi68shu9hkk2f FOREIGN KEY (id_vaccinator) REFERENCES public.role(id);


--
-- Name: vaccine_restriction fks7enjgokxv4kvc498lsfgsy82; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccine_restriction
    ADD CONSTRAINT fks7enjgokxv4kvc498lsfgsy82 FOREIGN KEY (fk_vaccine) REFERENCES public.vaccine(id);


--
-- Name: logisticpartner_package fksctew2oxsv543150ihomv083h; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.logisticpartner_package
    ADD CONSTRAINT fksctew2oxsv543150ihomv083h FOREIGN KEY (logisticpartner_id) REFERENCES public.logisticpartner(id);


--
-- Name: authority_vaccinationplan fkshtbfs3twfiv9kv84qepoj5sv; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.authority_vaccinationplan
    ADD CONSTRAINT fkshtbfs3twfiv9kv84qepoj5sv FOREIGN KEY (id) REFERENCES public.vaccinationplan(id);


--
-- Name: vaccinationcenter_vaccination fktrkp5ag147qkjde301rochvdh; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationcenter_vaccination
    ADD CONSTRAINT fktrkp5ag147qkjde301rochvdh FOREIGN KEY (vaccinationcenter_id) REFERENCES public.vaccinationcenter(id);


--
-- Name: vaccinationcenter_package fkwl6ykrcj7igygoaimp0852p1; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY public.vaccinationcenter_package
    ADD CONSTRAINT fkwl6ykrcj7igygoaimp0852p1 FOREIGN KEY (id) REFERENCES public.package(id);


--
-- PostgreSQL database dump complete
--

