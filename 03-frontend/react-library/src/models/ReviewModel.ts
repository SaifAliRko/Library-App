class ReviewModel {
  constructor(
    public id: number,
    public book_id: number,
    public userEmail: string,
    public date: string,
    public rating: number,
    public reviewDescription?: string
  ) {
    this.id = id
    this.book_id = book_id
    this.userEmail = userEmail
    this.date = date
    this.rating = rating
    this.reviewDescription = reviewDescription || ''
  }
}
export default ReviewModel
