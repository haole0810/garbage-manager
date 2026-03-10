import React, { useState, useEffect } from "react";
import { propertyService } from "../propertyService";
import type { Property } from "../types";

interface Props {
  areaId: string;
  onBuildingChange: (id: string) => void;
  onBlockChange: (id: string) => void;
}

const BuildingFilter: React.FC<Props> = ({
  areaId,
  onBuildingChange,
  onBlockChange,
}) => {
  const [buildings, setBuildings] = useState<Property[]>([]);
  const [selectedBuilding, setSelectedBuilding] = useState<Property | null>(
    null,
  );
  const [blocks, setBlocks] = useState<Property[]>([]);

  // 1. Dùng kỹ thuật reset state khi Props thay đổi (Không dùng Effect)
  const [prevAreaId, setPrevAreaId] = useState(areaId);
  if (areaId !== prevAreaId) {
    setPrevAreaId(areaId);
    setBuildings([]); // Reset ngay lập tức trong quá trình render
    setSelectedBuilding(null);
    setBlocks([]);
  }

  // 2. Effect CHỈ dùng để gọi API (Bất đồng bộ)
  useEffect(() => {
    if (!areaId) return;

    let ignore = false;
    propertyService.getBuildingsByArea(areaId).then((data) => {
      if (!ignore) {
        setBuildings(data);
      }
    });

    return () => {
      ignore = true;
    };
  }, [areaId]);

  const handleBuildingSelect = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const id = e.target.value;
    const found = buildings.find((b) => b.id.toString() === id) || null;

    setSelectedBuilding(found);
    onBuildingChange(id);
    setBlocks([]);

    if (found?.type === "APARTMENT_BUILDING") {
      propertyService.getSubUnits(id).then(setBlocks);
    } else {
      onBlockChange("");
    }
  };

  return (
    <div className="flex gap-4 animate-in fade-in duration-300">
      <div className="flex flex-col gap-2">
        <label className="text-[10px] font-bold text-slate-400 uppercase ml-2">
          Tòa nhà / Số nhà
        </label>
        <select
          value={selectedBuilding?.id || ""}
          onChange={handleBuildingSelect}
          className="p-3 bg-slate-50 border-none rounded-2xl outline-none focus:ring-2 focus:ring-emerald-500 w-64 shadow-sm"
        >
          <option value="">-- Chọn nhà --</option>
          {buildings.map((b) => (
            <option key={b.id} value={b.id}>
              {b.type === "APARTMENT_BUILDING" ? "🏢 " : "🏠 "}{" "}
              {b.addressNumber}
            </option>
          ))}
        </select>
      </div>

      {selectedBuilding?.type === "APARTMENT_BUILDING" && (
        <div className="flex flex-col gap-2 animate-in slide-in-from-left-4">
          <label className="text-[10px] font-bold text-slate-400 uppercase ml-2">
            Dãy / Block
          </label>
          <select
            onChange={(e) => onBlockChange(e.target.value)}
            className="p-3 bg-emerald-50 border-none rounded-2xl outline-none focus:ring-2 focus:ring-emerald-500 w-48 shadow-sm font-medium"
          >
            <option value="">-- Chọn dãy --</option>
            {blocks.map((bl) => (
              <option key={bl.id} value={bl.id}>
                {bl.addressNumber}
              </option>
            ))}
          </select>
        </div>
      )}
    </div>
  );
};

export default BuildingFilter;
