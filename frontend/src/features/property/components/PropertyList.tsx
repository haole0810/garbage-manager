import React from "react";
import type { Property } from "../types";
import { ChevronRight, Hash } from "lucide-react";

interface Props {
  data: Property[];
}

const PropertyList: React.FC<Props> = ({ data }) => {
  if (data.length === 0)
    return (
      <div className="bg-white rounded-3xl p-10 text-center border border-dashed border-slate-200">
        <p className="text-slate-400">Không có dữ liệu hộ dân.</p>
      </div>
    );

  return (
    <div className="bg-white rounded-3xl shadow-sm border border-slate-100 overflow-hidden">
      <table className="w-full text-left border-collapse">
        <thead>
          <tr className="bg-slate-50/50 border-b border-slate-100">
            <th className="px-6 py-4 text-[10px] font-black text-slate-400 uppercase">
              Mã/Số nhà
            </th>
            <th className="px-6 py-4 text-[10px] font-black text-slate-400 uppercase">
              Loại
            </th>
            <th className="px-6 py-4 text-[10px] font-black text-slate-400 uppercase">
              Chủ hộ
            </th>
            <th className="px-6 py-4 text-[10px] font-black text-slate-400 uppercase">
              Nợ đọng
            </th>
            <th className="px-6 py-4"></th>
          </tr>
        </thead>
        <tbody className="divide-y divide-slate-50">
          {data.map((item) => (
            <tr
              key={item.id}
              className="hover:bg-slate-50/80 transition-colors group"
            >
              <td className="px-6 py-3">
                <div className="flex items-center gap-3">
                  <div className="p-1.5 bg-slate-100 rounded-lg text-slate-500">
                    <Hash size={12} />
                  </div>
                  <span className="font-bold text-slate-700 text-sm">
                    {item.addressNumber}
                  </span>
                </div>
              </td>
              <td className="px-6 py-3 text-[11px] font-bold">
                <span
                  className={
                    item.type === "ROOM" ? "text-blue-500" : "text-amber-600"
                  }
                >
                  {item.type === "ROOM" ? "PHÒNG" : "NHÀ"}
                </span>
              </td>
              <td className="px-6 py-3 text-sm text-slate-600">
                {item.ownerName || "---"}
              </td>
              <td className="px-6 py-3 font-black text-sm text-rose-500">
                {item.totalDebt?.toLocaleString()}đ
              </td>
              <td className="px-6 py-3 text-right">
                <button className="opacity-0 group-hover:opacity-100 p-2 hover:bg-emerald-100 text-slate-400 hover:text-emerald-600 rounded-lg transition-all">
                  <ChevronRight size={16} />
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};
export default PropertyList;
