import {Dispatch, MutableRefObject, SetStateAction, useState} from "react";
import {handleSignInputChange, handleSignInputFocus} from "@/pages/sign-in/handlers";
import {SignInInputs, SignUpInputs} from "@/types/SignInInputs";

type InputElementProps = {
    inputRef: MutableRefObject<HTMLInputElement | null>,
    setIsFocused: Dispatch<SetStateAction<boolean>>,
    id: keyof SignInInputs | keyof SignUpInputs,
    signInputsState: {
        inputsData: SignInInputs | SignUpInputs,
        setInputsData: Dispatch<SetStateAction<SignInInputs | SignUpInputs>>,
    },
}

const SingInputElement = (props: InputElementProps) => {
    const {inputRef, id, setIsFocused, signInputsState} = props;
    const {inputsData, setInputsData} = signInputsState;
    // @ts-ignore
    const value = inputsData[id];

    return (
        <input
            value={value}
            onChange={e => handleSignInputChange(e, setInputsData, id)}
            autoComplete={'off'}
            className={"w-full outline-none z-10"}
            ref={inputRef}
            type="text"
            onBlur={() => setIsFocused(false)}
        />
    )
}

export default SingInputElement;