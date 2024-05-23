import {LayoutProps} from "@/types/LayoutProps";
import { motion } from "framer-motion";

const NavbarLayout = (props: LayoutProps) => {
    const {children} = props;

    return (
        <motion.div
            initial={{opacity: 0}}
            animate={{opacity: 1}}
            transition={{duration: 0.3, easings: "easeOut"}}
            className="flex flex-col min-w-67.5 bg-white h-svh top-0 sticky">
            {children}
        </motion.div>
    )
}

export default NavbarLayout;