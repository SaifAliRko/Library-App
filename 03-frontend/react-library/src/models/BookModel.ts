export class BookModel {
  // id , title , author? , description?, copies?,copiesAvailable?,category?, img?
  constructor(
    public id: number,
    public title: string,
    public author: string,
    public description?: string,
    public copies?: number,
    public copiesAvailable?: number,
    public category?: string,
    public img?: string
  ) {
    this.id = id
    this.title = title
    this.author = author
    this.description = description || ''
    this.copies = copies || 0
    this.copiesAvailable = copiesAvailable || 0
    this.category = category || ''
    this.img = img || ''
  }
}
