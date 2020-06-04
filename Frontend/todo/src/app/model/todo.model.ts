export class TodoModel {

    constructor(
        private id: number,
        private title: string,
        private isComplete: boolean,
        private createdOn: Date
    ){

    }
}
