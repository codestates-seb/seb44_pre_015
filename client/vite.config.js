import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      "/questions": {
        target: "http://ec2-13-125-172-34.ap-northeast-2.compute.amazonaws.com:8080",
        changeOrigin: true,
        secure: false,
        rewrite: (path) => path.replace(/^\/api/, ""),
      },
      "/members": {
        target: "http://ec2-13-125-172-34.ap-northeast-2.compute.amazonaws.com:8080",
        changeOrigin: true,
        secure: false,
        rewrite: (path) => path.replace(/^\/api/, ""),
      },
      "/answers": {
        target: "http://ec2-13-125-172-34.ap-northeast-2.compute.amazonaws.com:8080",
        changeOrigin: true,
        secure: false,
        rewrite: (path) => path.replace(/^\/api/, ""),
      },
      "/comments": {
        target: "http://ec2-13-125-172-34.ap-northeast-2.compute.amazonaws.com:8080",
        changeOrigin: true,
        secure: false,
        rewrite: (path) => path.replace(/^\/api/, ""),
      }
    },
  },
})
