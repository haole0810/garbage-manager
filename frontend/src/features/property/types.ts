export interface Property {
  id: number;
  addressNumber: string;
  ownerName: string;
  type: 'APARTMENT_BUILDING' | 'HOUSE' | 'ROOM' | 'BLOCK'; // Các loại nhà
  totalDebt: number;
  parentId?: number; // Có thể có hoặc không (dành cho căn hộ con)
}

export interface Area {
  id: number;
  name: string;
}