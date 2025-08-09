import './App.css'
import { Carousel } from './layouts/HomePage/components/Carousel'
import { ExploreTopBooks } from './layouts/HomePage/components/ExploreTopBooks'
import { Heros } from './layouts/HomePage/components/Heros'
import { LibraryServices } from './layouts/HomePage/components/LibraryServices'
import { HomePage } from './layouts/HomePage/HomePage'
import { Footer } from './layouts/NavbarAndFooter/Footer'
import { Navbar } from './layouts/NavbarAndFooter/Navbar'

export const App = () => {
  return (
    <div>
      <Navbar />
      <HomePage />
      <Footer />
    </div>
  )
}
