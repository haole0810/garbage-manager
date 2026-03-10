import type { Property, Area } from './types';

const API_URL = "http://localhost:8080/api";

export const propertyService = {
  // Lấy danh sách tuyến đường
  getAreas: (): Promise<Area[]> => 
    fetch(`${API_URL}/areas`)
      .then(res => {
        if (!res.ok) throw new Error("Không thể kết nối API Areas");
        return res.json();
      }),

  // Lấy Tòa nhà theo Area
  getBuildingsByArea: (areaId: string): Promise<Property[]> => 
    fetch(`${API_URL}/properties/area/${areaId}/buildings`)
      .then(res => {
        if (!res.ok) throw new Error("Không thể kết nối API Buildings");
        return res.json();
      }),

  // Lấy các con (Block/Phòng) theo ID cha
  getSubUnits: (parentId: string): Promise<Property[]> => 
    fetch(`${API_URL}/properties/parent/${parentId}`)
      .then(res => {
        if (!res.ok) throw new Error("Không thể kết nối API SubUnits");
        return res.json();
      }),
};