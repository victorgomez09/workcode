export const getInitialsFromString = (text: string): string => {
    return text.match(/(^\S\S?|\b\S)?/g)?.join("").match(/(^\S|\S$)?/g)?.join("").toUpperCase() || "";
}