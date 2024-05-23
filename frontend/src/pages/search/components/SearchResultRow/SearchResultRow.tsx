import SearchResultRowLayout from "./SearchResultRowLayout";
import {Song} from "@/types/Song";
import {User} from "@/types/User";
import {Album} from "@/types/Album";
import {Playlist} from "@/types/Playlist";

type SearchResultProps = {
    item: Song | User | Album | Playlist;
}

const SearchResultRow = (props: SearchResultProps) => {
    const {item} = props;

    console.log(item);

    return (
        <SearchResultRowLayout>
            <span>{item.song && item.song.name}</span>
        </SearchResultRowLayout>
    )
}

export default SearchResultRow;