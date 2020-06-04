export class TodoModel {

    constructor(
        public id: number,
        public title: string,
        public isComplete: boolean,
        public createdOn: Date
    ){

    }
}
