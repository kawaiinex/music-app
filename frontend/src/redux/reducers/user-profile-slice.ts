import {createSlice} from '@reduxjs/toolkit';
import {Playlist} from "@/types/Playlist";

type UserProfileState = {
    username: string | null,
    avatarPath: string | null,
    id: number | null,
    playlists: Playlist[] | null,
}

const initialState: UserProfileState = {
    username: null,
    avatarPath: null,
    id: null,
    playlists: null,
};

export const userProfileSlice = createSlice({
    name: 'userProfile',
    initialState,
    reducers: {
        setUserProfile: (state, action) => {
            state.username = action.payload.username;
            state.playlists = action.payload.playlists;
            state.id = action.payload.id;
            state.avatarPath = action.payload.avatarPath;
        }
    },
});

export const {setUserProfile} = userProfileSlice.actions;
export default userProfileSlice.reducer;