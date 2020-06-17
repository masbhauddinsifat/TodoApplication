export class TodoModel {

    constructor(
        public id: number,
        public title: string,
        public complete: boolean,
        public createdOn: Date
    ){

    }
}
