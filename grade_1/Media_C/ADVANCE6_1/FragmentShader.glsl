#ifdef GL_ES
precision mediump float;
precision mediump int;
#endif

#define PROCESSING_LIGHT_SHADER

uniform float fraction;

varying vec4 vertColor;
varying vec3 vertNormal;
varying vec3 vertLightDir;

void main() {
  int n = 10;
  float intensity;
  float lightDot = dot(vertLightDir, vertNormal);
  int hoge = int(lightDot*n);
  intensity = float(hoge)/n;
  if(intensity>1) intensity = 1;
  if(intensity<0) intensity = 0;
  vec3 c = vertColor.xyz * intensity;
  gl_FragColor = vec4(c.x, c.y, c.z, 1.0);
}