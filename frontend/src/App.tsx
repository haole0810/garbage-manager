import { useState, useEffect } from "react";
import axios from "axios";

// Định nghĩa kiểu dữ liệu giống DTO bên Java
interface Area {
  id: number;
  name: string;
}

interface Property {
  id: number;
  addressNumber: string;
  ownerName: string;
  type: string;
  totalDebt: number;
}

function App() {
  const [areas, setAreas] = useState<Area[]>([]);
  const [selectedAreaId, setSelectedAreaId] = useState<string>("");
  const [properties, setProperties] = useState<Property[]>([]);

  // 1. Load danh sách đường khi vừa vào trang
  useEffect(() => {
    axios
      .get("http://localhost:8080/api/areas")
      .then((res) => setAreas(res.data))
      .catch((err) => console.error("Lỗi lấy danh sách đường:", err));
  }, []);

  // 2. Load danh sách nhà khi người dùng chọn đường khác
  useEffect(() => {
    if (selectedAreaId) {
      axios
        .get(`http://localhost:8080/api/properties/area/${selectedAreaId}`)
        .then((res) => setProperties(res.data))
        .catch((err) => console.error("Lỗi lấy danh sách nhà:", err));
    } else {
      setProperties([]);
    }
  }, [selectedAreaId]);

  return (
    <div style={{ padding: "20px", fontFamily: "Arial", color: "black" }}>
      <h1>Quản Lý Thu Phí Rác</h1>

      {/* Dropdown chọn đường */}
      <div style={{ marginBottom: "20px" }}>
        <label>Chọn tuyến đường: </label>
        <select
          value={selectedAreaId}
          onChange={(e) => setSelectedAreaId(e.target.value)}
          style={{ padding: "5px", minWidth: "200px" }}
        >
          <option value="">-- Chọn con đường --</option>
          {areas.map((area) => (
            <option key={area.id} value={area.id}>
              {area.name}
            </option>
          ))}
        </select>
      </div>

      {/* Bảng danh sách nhà */}
      <table
        border={1}
        cellPadding={10}
        style={{ width: "100%", borderCollapse: "collapse" }}
      >
        <thead>
          <tr style={{ backgroundColor: "#f2f2f2" }}>
            <th>Số nhà/Phòng</th>
            <th>Chủ hộ</th>
            <th>Loại</th>
            <th>Tiền nợ hiện tại</th>
            <th>Thao tác</th>
          </tr>
        </thead>
        <tbody>
          {properties.length > 0 ? (
            properties.map((p) => (
              <tr key={p.id}>
                <td>{p.addressNumber}</td>
                <td>{p.ownerName}</td>
                <td>{p.type}</td>
                <td
                  style={{
                    color: p.totalDebt > 0 ? "red" : "green",
                    fontWeight: "bold",
                  }}
                >
                  {p.totalDebt.toLocaleString()} VNĐ
                </td>
                <td>
                  <button
                    onClick={() => alert("Chức năng thu tiền đang phát triển!")}
                  >
                    Thu tiền
                  </button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan={5} style={{ textAlign: "center" }}>
                Vui lòng chọn đường để xem danh sách
              </td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}

export default App;
