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
  float intensity;
  intensity = max(0.0, dot(vertLightDir, vertNormal));
  vec3 c = vertColor.xyz * intensity;
  float g = 0.29 * c.x + 0.58 * c.y + 0.11 * c.z;
  vec3 sepia = vec3(1.07, 0.74, 0.43) * g;
  gl_FragColor = vec4(sepia.x, sepia.y, sepia.z, 1.0);
}