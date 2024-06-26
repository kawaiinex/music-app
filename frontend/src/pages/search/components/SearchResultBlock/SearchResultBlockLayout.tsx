import {LayoutProps} from "@/types/LayoutProps";

const SearchResultBlockLayout = (props: LayoutProps) => {
    const {children} = props;

    return (
        <div>
            {children}
        </div>
    )
}

export default SearchResultBlockLayout;