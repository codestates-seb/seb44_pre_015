import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  // server: {
  //   proxy: {
  //     '/login': 'http://ec2-13-125-172-34.ap-northeast-2.compute.amazonaws.com:8080',
  //   },
  // }
})
