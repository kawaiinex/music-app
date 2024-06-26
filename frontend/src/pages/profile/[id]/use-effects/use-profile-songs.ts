import {useEffect, useState} from "react";
import {profileListsRequest} from "@/api/profile-lists-request";
import {SearchTab} from "@/types/SearchTab";
import {Song} from "@/types/Song";
import {useRouter} from "next/router";
import {useParams} from "next/navigation";
import {getImagesForSongs} from "@/utils/utils";

export const useProfileSongs = () => {
    const [songs, setSongs] = useState<Song[] | null>(null);
    const router = useRouter();
    const params = useParams();

    useEffect(() => {
        if(!router.isReady) return;
        const id = parseInt(params.id as string);

        const getData = async () => {
            const response = await profileListsRequest(id, SearchTab.Songs, 30, 0);
            await getImagesForSongs(response);
            setSongs(response.songs);
        }

        getData();
    }, [router]);

    return songs;
}