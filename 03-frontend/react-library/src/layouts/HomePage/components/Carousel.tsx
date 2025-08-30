import { useEffect, useState } from 'react'
import { BookModel } from '../../../models/BookModel'
import { ReturnBook } from './ReturnBook'
import { SpinnerLoading } from '../../Utils/SpinnerLoading'
import { Link } from 'react-router-dom'

export const Carousel = () => {
  const [books, setBooks] = useState<BookModel[]>([])
  const [isLoading, setIsLoading] = useState(true)
  const [httpError, setHttpError] = useState<string | null>(null)

  useEffect(() => {
    const fetchBooks = async () => {
      const baseUrl = 'http://localhost:8080/api/books'
      const url: string = `${baseUrl}?page=0&size=9`
      setIsLoading(true)
      setHttpError(null)

      try {
        const response = await fetch(url)
        setIsLoading(true)
        if (!response.ok) {
          throw new Error('Failed to fetch books')
        }
        const data = await response.json()
        const responseData = data._embedded.books
        const loadedBooks: BookModel[] = responseData.map((book: any) => ({
          id: book.id,
          title: book.title,
          author: book.author,
          description: book.description || '',
          copies: book.copies || 0,
          copiesAvailable: book.copiesAvailable || 0,
          category: book.category || '',
          img: book.img || ''
        }))
        setBooks(loadedBooks)
        setIsLoading(false)
      } catch (error: any) {
        setHttpError(error.message)
      } finally {
        setIsLoading(false)
      }
    }

    fetchBooks()
  }, [])
  if (isLoading) {
    return <SpinnerLoading />
  }
  if (httpError) {
    return (
      <div className="container mt-5">
        <p>{httpError}</p>
      </div>
    )
  }
  return (
    <div
      className="container mt-5"
      style={{ height: 550 }}
    >
      <div className="homepage-carousel-title">
        <h3>Find your next "I stayed up too late reading" book.</h3>
      </div>
      <div
        id="carouselExampleControls"
        className="carousel carousel-dark slide mt-5 
                d-none d-lg-block"
        data-bs-interval="false"
      >
        {/* Desktop */}
        <div className="carousel-inner">
          <div className="carousel-item active">
            <div className="row d-flex justify-content-center align-items-center">
              {books.slice(0, 3).map((book) => (
                <ReturnBook
                  key={book.id}
                  book={book}
                />
              ))}
            </div>
          </div>
          <div className="carousel-item">
            <div className="row d-flex justify-content-center align-items-center">
              {books.slice(3, 6).map((book) => (
                <ReturnBook
                  key={book.id}
                  book={book}
                />
              ))}
            </div>
          </div>
          <div className="carousel-item">
            <div className="row d-flex justify-content-center align-items-center">
              {books.slice(6, 9).map((book) => (
                <ReturnBook
                  key={book.id}
                  book={book}
                />
              ))}
            </div>
          </div>
          <button
            className="carousel-control-prev"
            type="button"
            data-bs-target="#carouselExampleControls"
            data-bs-slide="prev"
          >
            <span
              className="carousel-control-prev-icon"
              aria-hidden="true"
            ></span>
            <span className="visually-hidden">Previous</span>
          </button>
          <button
            className="carousel-control-next"
            type="button"
            data-bs-target="#carouselExampleControls"
            data-bs-slide="next"
          >
            <span
              className="carousel-control-next-icon"
              aria-hidden="true"
            ></span>
            <span className="visually-hidden">Next</span>
          </button>
        </div>
      </div>

      {/* Mobile */}
      <div className="d-lg-none mt-3">
        <ReturnBook
          key={books[7].id}
          book={books[7]}
        />
      </div>
      <div className="homepage-carousel-title mt-3">
        <Link
          className="btn btn-outline-secondary btn-lg"
          to="/search"
        >
          View More
        </Link>
      </div>
    </div>
  )
}
