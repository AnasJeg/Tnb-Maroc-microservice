export class PaginationValue<T> {
    public content?: Array<T>;
    public last?: boolean
    public totalPages?: number
    public totalElements?: number
    public size: number = 6
    public number?: number
    public first?: boolean
    public numberOfElements?: number
    public empty?: boolean
}