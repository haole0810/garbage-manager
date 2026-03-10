import React, { useState, useEffect } from "react";
import BuildingFilter from "../components/BuildingFilter";
import AreaFilter from "../components/AreaFilter";
import PropertyList from "../components/PropertyList";
import { propertyService } from "../propertyService";
import type { Property } from "../types";

const PropertyPage: React.FC = () => {
  const [filters, setFilters] = useState({
    areaId: "",
    buildingId: "",
    blockId: "",
  });
  const [displayData, setDisplayData] = useState<Property[]>([]);
  const [loading, setLoading] = useState(false);

  // Kỹ thuật Reset State đồng bộ (Tránh lỗi Cascading)
  const [prevAreaId, setPrevAreaId] = useState(filters.areaId);
  if (filters.areaId !== prevAreaId) {
    setPrevAreaId(filters.areaId);
    setDisplayData([]);
  }

  useEffect(() => {
    if (!filters.areaId) return;
    let ignore = false;
    setLoading(true);

    const loadData = async () => {
      try {
        let data: Property[] = [];
        if (filters.blockId)
          data = await propertyService.getSubUnits(filters.blockId);
        else if (filters.buildingId)
          data = await propertyService.getSubUnits(filters.buildingId);
        else data = await propertyService.getBuildingsByArea(filters.areaId);

        if (!ignore) setDisplayData(data);
      } finally {
        if (!ignore) setLoading(false);
      }
    };
    loadData();
    return () => {
      ignore = true;
    };
  }, [filters]);

  return (
    <div className="p-6 max-w-6xl mx-auto">
      <div className="flex items-center justify-between mb-8">
        <h1 className="text-2xl font-black text-slate-800 tracking-tight italic">
          HỘ DÂN TRÊN TUYẾN
        </h1>
        <div className="text-[10px] font-bold text-slate-400 bg-white px-4 py-2 rounded-full border border-slate-100">
          {loading ? "ĐANG TẢI..." : `SỐ LƯỢNG: ${displayData.length}`}
        </div>
      </div>

      <div className="flex items-center gap-4 mb-6 bg-white p-4 rounded-2xl shadow-sm border border-slate-100">
        <AreaFilter
          value={filters.areaId}
          onChange={(id) =>
            setFilters({ areaId: id, buildingId: "", blockId: "" })
          }
        />
        {filters.areaId && (
          <BuildingFilter
            key={filters.areaId}
            areaId={filters.areaId}
            onBuildingChange={(id) =>
              setFilters({ ...filters, buildingId: id, blockId: "" })
            }
            onBlockChange={(id) => setFilters({ ...filters, blockId: id })}
          />
        )}
      </div>

      {loading ? (
        <div className="flex flex-col items-center justify-center p-20">
          <div className="w-10 h-10 border-4 border-emerald-500 border-t-transparent rounded-full animate-spin mb-4"></div>
          <p className="text-xs font-bold text-slate-300 uppercase tracking-widest">
            Đang lấy dữ liệu...
          </p>
        </div>
      ) : (
        <PropertyList data={displayData} />
      )}
    </div>
  );
};
export default PropertyPage;
