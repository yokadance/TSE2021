// @ts-ignore
import React from 'react';
import "./css/table-style.css";
import "./css/output.css";
// @ts-ignore
import PropTypes from 'prop-types';
// @ts-ignore
import {IpageActiveSet, Iprop, Irow, ItableLinks, ItableState} from "./types";

/**
 *@class
 * This Is the primary building block of a table designed in tailwind css
 * The abstraction is key in enforcing reacts component based system
 */
export default class Table extends React.Component<Iprop, ItableState> {
    state: ItableState;
    props: Iprop;
    private search_debounce;
    static defaultProps: {
        no_content_text: string;
        per_page: number;
        debounce_search: number;
        table_header: string;
    };
    static propTypes: {
        rows: PropTypes.Validator<any[]>;
        columns: PropTypes.Validator<any[]>;
        debounce_search: PropTypes.Requireable<number>;
        per_page: PropTypes.Requireable<number>;
        no_content_text: PropTypes.Requireable<string>;
        table_header: PropTypes.Requireable<string>;
    };
    constructor(props: Iprop);
    /**
     * React calls the render function of a component anytime props changes from its ancesstor
     * Even if the change is not from its direct parent. This becomes very technical for me since
     * It is a paginated component, recalculating pagination on every re-render can be very expensive
     * Since data rows can be very large. So the best bet is to comapare the nextProps with the previous.
     * So that everytime this component wants to re-rendered, I compare if there was
     * A change in the props. Secondly, this Function is not called on initialization, Thus further data about our table
     * Must be calculated here.
     *
     *  NOTE:: in vue js I could easily do this with watchers, or @observable in aurelia, unfortunately
     *      I could only achieve this with the depreciated methosd in React
     */
    UNSAFE_componentWillReceiveProps(nextProps: Iprop): void;
    static setInitialPage(rows: Irow[], per_page: number): IpageActiveSet;
    backButtonOnclick: (event: React.MouseEvent) => void;
    searchChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
    private processFilter;
    forwardButtonOnclick: (event: React.MouseEvent) => void;
    pageNumberClick: (event: React.MouseEvent, page_number: number) => void;
    setPage(page_number: number): void;
    private setComponentState;
    protected static setPageActive(data: ItableLinks, page_number: number): IpageActiveSet;
    render(): JSX.Element;
}
