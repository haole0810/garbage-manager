import React, { useState, useEffect } from "react";
import { propertyService } from "../propertyService";
import type { Area } from "../types";

interface Props {
  value: string;
  onChange: (id: string) => void;
}

const AreaFilter: React.FC<Props> = ({ value, onChange }) => {
  const [areas, setAreas] = useState<Area[]>([]);

  useEffect(() => {
    propertyService.getAreas().then(setAreas);
  }, []);

  return (
    <div className="flex flex-col gap-2">
      <label className="text-[10px] font-bold text-slate-400 uppercase ml-2">
        Tuyến đường
      </label>
      <select
        value={value}
        onChange={(e) => onChange(e.target.value)}
        className="p-3 bg-slate-50 border-none rounded-2xl outline-none focus:ring-2 focus:ring-emerald-500 w-64 shadow-sm"
      >
        <option value="">-- Chọn tuyến đường --</option>
        {areas.map((a) => (
          <option key={a.id} value={a.id.toString()}>
            {a.name}
          </option>
        ))}
      </select>
    </div>
  );
};
export default AreaFilter;
