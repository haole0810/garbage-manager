import React from "react";
import PropertyPage from "./features/property/pages/PropertyPage";
// import Navbar from './components/layout/Navbar'; // Nếu em đã làm xong Navbar

const App: React.FC = () => {
  return (
    <div className="min-h-screen bg-slate-50">
      {/* Nếu sau này có Navbar hoặc Sidebar, em sẽ đặt ở đây.
          Hiện tại chúng ta tập trung hiển thị trang Quản lý hộ dân.
      */}

      <main className="container mx-auto py-8 px-4">
        {/* Gọi trang quản lý có bộ lọc lồng nhau (Area -> Building -> Block) */}
        <PropertyPage />
      </main>

      {/* Footer đơn giản cho người trẻ */}
      <footer className="py-6 text-center text-slate-400 text-sm">
        &copy; 2024 Garbage Manager - Design for Young Generation
      </footer>
    </div>
  );
};

export default App;
