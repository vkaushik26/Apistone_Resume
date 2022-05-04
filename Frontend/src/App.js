import Experience from "./components/experience/Experience";
import Home from "./components/Home";
import { BrowserRouter } from 'react-router-dom';
import './login.css'
function App() {
  return (
    <div className="App">
      <BrowserRouter>
      <Home/>
      </BrowserRouter>
    </div>
  );
}

export default App;
